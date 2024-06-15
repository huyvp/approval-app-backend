package com.samsung.project.service;

import com.samsung.project.model.Authority;
import com.samsung.project.repo.AuthorityRepo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AuthorityService {

    AuthorityRepo authorityRepo;

    public List<Authority> getAuthoritiesByUsername(String username) {
        return this.authorityRepo.getAuthoritiesByUsername(username);
    }
}
