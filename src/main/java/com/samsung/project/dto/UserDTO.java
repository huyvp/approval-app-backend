package com.samsung.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserDTO {
    @NotBlank(message = "User name is require!")
    private String username;
    @NotBlank(message = "Password is require!")
    private String password;
    private String avatar;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @NotBlank(message = "Email is require!")
    private String email;
    private boolean status;
    private String firstName;
    private String lastName;
    private String phoneNo;
    private String gender;

    public UserDTO(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
