package com.samsung.project.controller;

import com.samsung.project.dto.UserDTO;
import com.samsung.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user/add")
    public ResponseEntity<?> createUser(@RequestBody @Valid UserDTO userDTO) {
        this.userService.createUser(userDTO);
        return ResponseEntity.ok("created user successfully");
    }
}
