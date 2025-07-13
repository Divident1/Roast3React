package com.wecp.library_management_system_jwt.config;

import javax.servlet.http.HttpServletResponse;

import com.wecp.library_management_system_jwt.jwt.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests()
            .antMatchers("/users/register", "/users/login").permitAll()
            
            // Admin only endpoints
            .antMatchers(HttpMethod.POST, "/books").hasAuthority("ADMIN")
            .antMatchers(HttpMethod.PUT, "/books/**").hasAuthority("ADMIN")
            .antMatchers(HttpMethod.DELETE, "/books/**").hasAuthority("ADMIN")
            
            // Admin or User endpoints
            .antMatchers(HttpMethod.GET, "/books").hasAnyAuthority("ADMIN", "USER")
            
            // User only endpoints (but not ADMIN)
            .antMatchers(HttpMethod.POST, "/books/**/borrow").hasAuthority("USER")
            .antMatchers(HttpMethod.POST, "/books/**/return").hasAuthority("USER")
            
            .antMatchers("/users/**").authenticated()
            .antMatchers("/user/**").hasAuthority("USER")
            .antMatchers("/admin/**").hasAuthority("ADMIN")
            
            .anyRequest().authenticated()
            .and()
            .exceptionHandling()
            .authenticationEntryPoint((request, response, exception) -> {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, exception.getMessage());
            })
            .accessDeniedHandler((request, response, exception) -> {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, exception.getMessage());
            })
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Add JWT filter
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
