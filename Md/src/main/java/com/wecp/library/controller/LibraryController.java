package com.wecp.library.controller;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.wecp.library.controller.exception.UserNotSubscribedException;
import com.wecp.library.domain.Issue;
import com.wecp.library.domain.User;
import com.wecp.library.repository.IssueRepository;
import com.wecp.library.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import java.util.Optional;

/**
 * REST controller for managing library system process
 */
@RestController
@RequestMapping("/api/v1")
public class LibraryController {

    /**
     * {@code POST  /issueBook} : Create a new issue.
     *
     * @param issue the issue to create.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     * the user, or if does not exist, return with status "noContent".
     * If user is not subscribed, throw {@link UserNotSubscribedException}
     * 
     */
    @Autowired private UserRepository userRepo;
    @Autowired private IssueRepository issueRepo;

    @PostMapping("/issue-book")
    public ResponseEntity<Issue> issueBook(@RequestBody Issue issue) {


        Optional<User> optUser=userRepo.findById(issue.getUser().getId());
        if(optUser.isPresent() && optUser.get().getSubscribed())
        {
           return ResponseEntity.ok(issue);
        }
        else{
            
            throw new UserNotSubscribedException("User is not subscribed");
        }
    }

    /**
     * {@code POST  /user} : Create a new user.
     *
     * @param user the user to create.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the new user
     */
    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userRepo.save(user);
        return ResponseEntity.ok(savedUser);
    }

    /**
     * {@code GET  /renew-user-subscription/:id} :  Send userId, set user subscription to true
     *
     * @param id the id of the user to renew subscription.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     * the user, or if does not exist, return with status "noContent".
     */
    @GetMapping("renew-user-subscription/{id}")
    public ResponseEntity<User> renewUserSubscription(@PathVariable Long id) {
        Optional<User> userOpt = userRepo.findById(id);
        if(userOpt.isPresent()){
        User newuser=userOpt.get();
        newuser.setSubscribed(true);
        userRepo.save(newuser);
            return ResponseEntity.ok(newuser);
        }else{
            return ResponseEntity.noContent().build();
        }
    }
}
