package com.samsung.project.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
public class UserResponse {
    String username;
    String avatar;
    String email;
    String firstName;
    String lastName;
    String phoneNo;
    String gender;
}
