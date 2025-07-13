package com.wecp.w3day5task1.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class HomeController {

    @GetMapping("/")
    public String hello()
    {
        return "Welcome";
    }
     @GetMapping("/admin")
    public String hello1()
    {
        return "Welcome Admin";
    }

    // Implement two endpoints:
    // GET /: This endpoint should be accessible to all authenticated users and return a "Welcome" message.
   // GET /admin: This endpoint should be accessible only to users with the ADMIN role and return a "Welcome Admin" message.
}
