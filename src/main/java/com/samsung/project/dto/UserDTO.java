package com.samsung.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserDTO {
    @NotBlank(message = "INVALID_USER_USERNAME")
    private String username;
    @NotBlank(message = "INVALID_USER_PASSWORD")
    private String password;
    private String avatar;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @Email(message = "INVALID_USER_EMAIL")
    private String email;
    private boolean status;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String gender;
}
