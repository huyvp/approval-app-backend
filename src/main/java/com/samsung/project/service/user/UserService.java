package com.samsung.project.service.user;

import com.samsung.project.model.Authority;
import com.samsung.project.model.User;
import com.samsung.project.repo.AuthorityRepo;
import com.samsung.project.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class UserService {
    private final UserRepo userRepo;
    private final AuthorityRepo authorityRepo;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public UserService(UserRepo userRepo, AuthorityRepo authorityRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.authorityRepo = authorityRepo;
        this.passwordEncoder = passwordEncoder;
    }
    public void createUser(User user) {
        Authority authority = new Authority();
        authority.setUsername(user.getUsername());
        authority.setAuthority("USER");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        userRepo.save(user);
        authorityRepo.saveAuthority(authority);
    }
    public User getUserByUserName(String username) {
        return this.userRepo.findUserByUsername(username);
    }
    public User getUserById(int id){
        return this.userRepo.findUserById(id);
    }
    public List<User> getAllUser(){
        return this.userRepo.findAllUser();
    }
    public Authority getAuthority(int id) {
        return this.userRepo.findUserAndAuthority(id);
    }
    public void auth(String username, String password) {

        if (username == null || password == null) {
            throw new BadCredentialsException("Invalid user or password!");
        }

        User user = userRepo.findUserByUsername(username);
        if (user != null) {
            boolean isMatch = passwordEncoder.matches(password, user.getPassword());
            if (!isMatch)
                throw new BadCredentialsException("Bad Credentials");
        } else {
            throw new BadCredentialsException("Bad Credentials!");
        }
    }
}
