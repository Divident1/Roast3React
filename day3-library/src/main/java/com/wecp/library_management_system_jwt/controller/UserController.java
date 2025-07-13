package com.wecp.library_management_system_jwt.controller;

import com.wecp.library_management_system_jwt.dto.AuthRequest;
import com.wecp.library_management_system_jwt.dto.AuthResponse;
import com.wecp.library_management_system_jwt.entity.User;
import com.wecp.library_management_system_jwt.service.UserService;
import com.wecp.library_management_system_jwt.jwt.JwtUtil;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        userService.registerUser(user);
        return ResponseEntity.ok(Map.of("username", user.getUsername()));
    }    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            final UserDetails userDetails = userService.loadUserByUsername(request.getUsername());
            final String token = jwtUtil.generateToken(userDetails);
            return ResponseEntity.ok(new AuthResponse(token));
        } catch (Exception e) {
            return ResponseEntity.status(401).body(Map.of("error", "Invalid username or password"));
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User user) {
        return ResponseEntity.ok(userService.updateUser(userId, user));
    }
}
