package com.samsung.project.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Data
public class UserDTO {
    @NotBlank(message = "VALID103")
    String username;
    @NotBlank(message = "VALID104")
    String password;
    String avatar;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    @Email(message = "VALID105")
    String email;
    boolean status;
    String firstName;
    String lastName;
    String phoneNumber;
    String gender;
}
