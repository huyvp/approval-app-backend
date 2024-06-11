package com.samsung.project.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
public class User {
    Integer id;
    String username;
    String password;
    String avatar;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    String email;
    boolean status;
    String firstName;
    String lastName;
    String phoneNo;
    String gender;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
