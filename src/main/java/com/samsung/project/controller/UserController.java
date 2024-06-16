package com.samsung.project.controller;

import com.samsung.project.dto.UserDTO;
import com.samsung.project.handler.ResponseHandler;
import com.samsung.project.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("${api.prefix}/user")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserController {
    UserService userService;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody @Valid UserDTO userDTO) {
        userService.createUser(userDTO);
        return ResponseHandler.execute(null);
    }

    @GetMapping()
    public ResponseEntity<?> getAllUser(@RequestParam int page, @RequestParam int size) {
        return ResponseHandler.execute(userService.getAllUser(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable int id) {
        return ResponseHandler.execute(userService.getUserById(id));
    }

    @GetMapping("/authority/{id}")
    public ResponseEntity<?> getAuthority(@PathVariable int id) {
        return ResponseEntity.ok(userService.getAuthority(id));
    }
}
