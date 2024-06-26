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
}
