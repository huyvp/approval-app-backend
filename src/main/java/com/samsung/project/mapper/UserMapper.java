package com.samsung.project.mapper;

import com.samsung.project.dto.UserDTO;
import com.samsung.project.model.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    User toUser(UserDTO userDTO);
}
