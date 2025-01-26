package com.example.medicalsystem.mapper;

import com.example.medicalsystem.dto.UserRequestDTO;
import com.example.medicalsystem.dto.UserResponseDTO;
import com.example.medicalsystem.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(UserRequestDTO requestDTO) {
        return User.builder()
                .username(requestDTO.getUsername())
                .fullName(requestDTO.getFullName())
                .password(requestDTO.getPassword())
                .email(requestDTO.getEmail())
                .phone(requestDTO.getPhone())
                .role(requestDTO.getRole())
                .status(User.Status.ACTIVE)
                .build();
    }

    public UserResponseDTO toResponse(User user) {
        return UserResponseDTO.builder()
                .username(user.getUsername())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .role(user.getRole())
                .status(user.getStatus())
                .build();
    }
}

