package com.samsung.project.controller;

import com.samsung.project.model.User;
import com.samsung.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user/add")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        this.userService.createUser(user);
        return ResponseEntity.ok("OK");
    }
}
