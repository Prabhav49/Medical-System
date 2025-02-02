package com.example.medicalsystem.mapper;

import com.example.medicalsystem.dto.UserRequestDTO;
import com.example.medicalsystem.dto.UserResponseDTO;
import com.example.medicalsystem.entity.User;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class UserMapper {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd MM yyyy");

    public User toEntity(UserRequestDTO requestDTO) {
        return User.builder()
                .username(requestDTO.getUsername())
                .fullName(requestDTO.getFullName())
                .password(requestDTO.getPassword())
                .email(requestDTO.getEmail())
                .phone(requestDTO.getPhone())
                .gender(User.Gender.valueOf(requestDTO.getGender().toUpperCase()))
                .dateOfBirth(requestDTO.getDateOfBirth())
                .address(requestDTO.getAddress())
                .status(User.Status.ACTIVE)
                .build();
    }

    public UserResponseDTO toResponse(User user) {
        return UserResponseDTO.builder()
                .username(user.getUsername())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .gender(user.getGender().name()) 
                .dateOfBirth(user.getDateOfBirth())
                .address(user.getAddress())
                .status(user.getStatus())
                .build();
    }
}
