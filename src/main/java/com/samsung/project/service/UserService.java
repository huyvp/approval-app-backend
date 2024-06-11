package com.samsung.project.service;

import com.samsung.project.dto.UserDTO;
import com.samsung.project.mapper.UserMapper;
import com.samsung.project.model.Authority;
import com.samsung.project.model.User;
import com.samsung.project.repo.AuthorityRepo;
import com.samsung.project.repo.UserRepo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepo userRepo;
    AuthorityRepo authorityRepo;
    PasswordEncoder passwordEncoder;
    UserMapper userMapper;

    public void createUser(UserDTO userDTO) {
        User existingUser = userRepo.findByName(userDTO.getUsername());
        if (existingUser != null) {
            throw new RuntimeException("Username already exists");
        }
        User user = User.builder()
                .username(userDTO.getUsername())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .email(userDTO.getEmail())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        userRepo.save(user);

        Authority authority = Authority.builder()
                .username(userDTO.getUsername())
                .authority("USER")
                .build();
        authorityRepo.saveAuthority(authority);
    }

    public User getUserByUserName(String username) {
        User user = this.userRepo.findByName(username);
        if (user == null) {
            throw new RuntimeException("user not found");
        }
        return user;
    }

    public User getUserById(int id) {
        User user = this.userRepo.findUserById(id);
        if (user == null) {
            throw new RuntimeException("user not found");
        }
        return user;
    }

    public List<User> getAllUser() {
        return this.userRepo.findAll();
    }

    public Authority getAuthority(int id) {
        Authority authority = this.userRepo.findUserAndAuthority(id);
        if (authority == null) {
            throw new RuntimeException("authority not found");
        }
        return authority;
    }

    public void auth(String username, String password) {

        if (username == null || password == null) {
            throw new BadCredentialsException("Invalid user or password!");
        }

        User user = userRepo.findByName(username);
        if (user != null) {
            boolean isMatch = passwordEncoder.matches(password, user.getPassword());
            if (!isMatch)
                throw new BadCredentialsException("Bad Credentials");
        } else {
            throw new BadCredentialsException("Bad Credentials!");
        }
    }
}
