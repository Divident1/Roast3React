package com.wecp.library_management_system_jwt.service;

import com.wecp.library_management_system_jwt.entity.User;
import com.wecp.library_management_system_jwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@Primary
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId)
            .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + userId));
    }

    public User updateUser(Long userId, User updatedUser) {
        User existingUser = userRepository.findById(userId)
            .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + userId));
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        existingUser.setRole(updatedUser.getRole());
        return userRepository.save(existingUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (!optionalUser.isPresent()) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        User user = optionalUser.get();
        return new org.springframework.security.core.userdetails.User(
            user.getUsername(),
            user.getPassword(),
            Collections.singleton(new SimpleGrantedAuthority(user.getRole()))
        );
    }
}
