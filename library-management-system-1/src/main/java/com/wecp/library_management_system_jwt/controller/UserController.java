package com.wecp.library_management_system_jwt.controller;

import com.wecp.library_management_system_jwt.dto.AuthRequest;
import com.wecp.library_management_system_jwt.dto.AuthResponse;
import com.wecp.library_management_system_jwt.entity.User;
import com.wecp.library_management_system_jwt.service.UserService;
import com.wecp.library_management_system_jwt.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        userService.registerUser(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginUser(@RequestBody AuthRequest authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );

        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthResponse(jwt, userDetails.getUsername()));
    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasAuthority('ADMIN') or #userId == authentication.principal.username")
    public ResponseEntity<User> getUserDetails(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{userId}")
    @PreAuthorize("hasAuthority('ADMIN') or #userId == authentication.principal.username")
    public ResponseEntity<User> updateUserDetails(@PathVariable Long userId, @RequestBody User user) {
        User updatedUser = userService.updateUser(userId, user);
        return ResponseEntity.ok(updatedUser);
    }
}