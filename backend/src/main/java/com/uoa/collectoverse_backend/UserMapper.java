package com.uoa.collectoverse_backend;

import com.uoa.collectoverse_backend.dto.UserDTO;
import com.uoa.collectoverse_backend.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    // Da Entity a DTO
    public UserDTO toDTO(User user){
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        return dto;
    }

    // Da DTO ad Entity
    public User toEntity(UserDTO dto){
        User user = new User();
        user.setId(dto.getId());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        return user;
    }
}
