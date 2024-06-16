package com.samsung.project.service;

import com.samsung.project.dto.UserDTO;
import com.samsung.project.exception.ApprovalException;
import com.samsung.project.exception.ErrorCode;
import com.samsung.project.mapper.UserMapper;
import com.samsung.project.model.Authority;
import com.samsung.project.model.User;
import com.samsung.project.repo.AuthorityRepo;
import com.samsung.project.repo.UserRepo;
import com.samsung.project.response.UserResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
            throw new ApprovalException(ErrorCode.APP301);
        }
        User user = userMapper.toUser(userDTO);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        userRepo.save(user);

        Authority authority = Authority.builder()
                .username(userDTO.getUsername())
                .authority("USER")
                .build();
        authorityRepo.save(authority);
    }

    public User getUserByUserName(String username) {
        User user = this.userRepo.findByName(username);
        if (user == null) {
            throw new ApprovalException(ErrorCode.APP302);
        }
        return user;
    }

    public UserResponse getUserById(int id) {
        User user = this.userRepo.findById(id);
        if (user == null) {
            throw new ApprovalException(ErrorCode.APP302);
        }
        return userMapper.toUserResponse(user);
    }

    public List<UserResponse> getAllUser(int page, int size) {
        List<User> users = this.userRepo.findAll(page * size, size);
        List<UserResponse> userResponses = new ArrayList<>();
        for (User user : users) {
            userResponses.add(userMapper.toUserResponse(user));
        }
        return userResponses;
    }

    public Authority getAuthority(int id) {
        Authority authority = this.userRepo.findUserAndAuthority(id);
        if (authority == null) {
            throw new ApprovalException(ErrorCode.AUTH401);
        }
        return authority;
    }

    public void auth(String username, String password) {

        if (username == null || password == null) {
            throw new ApprovalException(ErrorCode.AUTH402);
        }

        User user = userRepo.findByName(username);
        if (user != null) {
            boolean isMatch = passwordEncoder.matches(password, user.getPassword());
            if (!isMatch)
                throw new ApprovalException(ErrorCode.AUTH403);
        } else {
            throw new ApprovalException(ErrorCode.AUTH403);
        }
    }
}
