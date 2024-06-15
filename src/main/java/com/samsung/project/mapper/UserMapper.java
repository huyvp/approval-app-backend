package com.samsung.project.mapper;

import com.samsung.project.dto.UserDTO;
import com.samsung.project.model.User;
import com.samsung.project.response.UserResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserDTO userDTO);
    UserResponse toUserResponse(User user);
}
