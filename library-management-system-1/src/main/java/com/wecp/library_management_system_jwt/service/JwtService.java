package com.wecp.library_management_system_jwt.service;

import com.wecp.library_management_system_jwt.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    public String generateToken(UserDetails userDetails) {
        return jwtUtil.generateToken(userDetails);
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        return jwtUtil.validateToken(token, userDetails);
    }

    public UserDetails extractUserDetails(String token) {
        String username = jwtUtil.extractUsername(token);
        if (username != null) {
            return userDetailsService.loadUserByUsername(username);
        }
        throw new UsernameNotFoundException("User not found");
    }
}