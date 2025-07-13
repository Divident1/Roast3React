package com.wecp.w3day5task1.config;

import com.wecp.w3day5task1.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig  {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeHttpRequests(auth->
        auth.antMatchers("/").hasAnyRole("USER","ADMIN")
        .antMatchers("/admin").hasRole("ADMIN")
        .anyRequest().authenticated()
        ).httpBasic();
        return http.build();
    }

@Bean
    protected InMemoryUserDetailsManager userDetailsManager(PasswordEncoder passwordEncoder)
    {
        UserDetails user =
        User.withUsername("user")
        .password(passwordEncoder().encode("user@123"))
        .roles("USER")
        .build();

        UserDetails admin=
        User.withUsername("admin")
        .password(passwordEncoder().encode("admin@123"))
        .roles("ADMIN")
        .build();


        return new InMemoryUserDetailsManager(user,admin);
    }



   /*  @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Using the CustomUserDetailsService for authentication
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    } */

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
