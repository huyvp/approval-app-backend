package com.samsung.project.controller;

import com.samsung.project.model.User;
import com.samsung.project.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user){
        userService.createUser(user);
        return ResponseEntity.ok("created user");
    }
    @GetMapping
    public ResponseEntity<?> getAllUser(){
        return ResponseEntity.ok(userService.getAllUser());
    }
    @GetMapping("/{username}")
    public ResponseEntity<?> getUserByUserName(@PathVariable String username){
        return ResponseEntity.ok(userService.getUserByUserName(username));
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<?> getUserById(@PathVariable int id){
        return ResponseEntity.ok(userService.getUserById(id));
    }
    @GetMapping("/authority/{id}")
    public  ResponseEntity<?> getAuthority(@PathVariable int id){
        return ResponseEntity.ok(userService.getAuthority(id));
    }
}