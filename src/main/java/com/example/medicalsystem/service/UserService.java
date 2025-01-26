package com.example.medicalsystem.service;


import com.example.medicalsystem.dto.UserRequestDTO;
import com.example.medicalsystem.dto.UserResponseDTO;
import com.example.medicalsystem.entity.User;
import com.example.medicalsystem.mapper.UserMapper;
import com.example.medicalsystem.repo.UserRepository;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserResponseDTO registerUser(UserRequestDTO userRequestDTO) {
        if (userRepository.existsByEmail(userRequestDTO.getEmail())) {
            throw new IllegalArgumentException("Email already in use");
        }
        if (userRepository.existsByPhone(userRequestDTO.getPhone())) {
            throw new IllegalArgumentException("Phone number already in use");
        }
        User userEntity = userMapper.toEntity(userRequestDTO);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        User savedUser = userRepository.save(userEntity);
        return userMapper.toResponse(savedUser);
    }
}
