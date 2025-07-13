package com.wecp.library_management_system_jwt.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.wecp.library_management_system_jwt.entity.User;
import com.wecp.library_management_system_jwt.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    // @Override
    // public UserDetails loadUserByUsername(String username) throws
    // UsernameNotFoundException {
    // Optional<User> u=userRepository.findByUsername(username);
    // if(u==null){
    // throw new UsernameNotFoundException("Username not found");
    // }else{
    // return new
    // org.springframework.security.core.userdetails.User(u.getUsername(),
    // u.getPassword(), new ArrayList<>());
    // }
    // }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                new ArrayList<>());
        // }

    }
}