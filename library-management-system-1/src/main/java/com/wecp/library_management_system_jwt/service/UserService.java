package com.wecp.library_management_system_jwt.service;

import com.wecp.library_management_system_jwt.entity.User;
import com.wecp.library_management_system_jwt.exception.UserNotFoundException;
import com.wecp.library_management_system_jwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
    }

    public User updateUser(Long userId, User userDetails) {
        User user = findUserById(userId);
        user.setUsername(userDetails.getUsername());
        user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        user.setRole(userDetails.getRole());
        return userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        User user = findUserById(userId);
        userRepository.delete(user);
    }
}