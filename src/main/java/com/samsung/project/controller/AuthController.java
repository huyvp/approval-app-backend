package com.samsung.project.controller;

import com.samsung.project.dto.UserDTO;
import com.samsung.project.handler.ResponseHandler;
import com.samsung.project.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth/user")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PUBLIC, makeFinal = true)
public class AuthController {
    UserService userService;

    @PostMapping()
    ResponseEntity<?> createUser(@RequestBody @Valid UserDTO userDTO) {
        this.userService.createUser(userDTO);
        return ResponseHandler.execute(null);
    }
}
