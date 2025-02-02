package com.example.medicalsystem.service;


import com.example.medicalsystem.dto.UserRequestDTO;
import com.example.medicalsystem.dto.UserResponseDTO;
import com.example.medicalsystem.dto.UserUpdateDTO;
import com.example.medicalsystem.entity.User;
import com.example.medicalsystem.mapper.UserMapper;
import com.example.medicalsystem.repo.UserRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

    public UserResponseDTO getUserInfo(String username){
        User user = userRepository.findByUsername(username);
        if(user == null) return null;
        return userMapper.toResponse(user);
    }
    
    public String updateUserDetails(Integer id, UserUpdateDTO userUpdateDTO) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return "User not found";
        }

        StringBuilder updatedFields = new StringBuilder();
        DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd MM yyyy");

        if (userUpdateDTO.getFullName() != null && !userUpdateDTO.getFullName().equals(user.getFullName())) {
            user.setFullName(userUpdateDTO.getFullName());
            updatedFields.append("fullName, ");
        }
        if (userUpdateDTO.getPassword() != null && !userUpdateDTO.getPassword().equals(user.getPassword())) {
            user.setPassword(passwordEncoder.encode(userUpdateDTO.getPassword()));
            updatedFields.append("password, ");
        }
        if (userUpdateDTO.getEmail() != null && !userUpdateDTO.getEmail().equals(user.getEmail())) {
            user.setEmail(userUpdateDTO.getEmail());
            updatedFields.append("email, ");
        }
        if (userUpdateDTO.getPhone() != null && !userUpdateDTO.getPhone().equals(user.getPhone())) {
            user.setPhone(userUpdateDTO.getPhone());
            updatedFields.append("phone, ");
        }
        
        if (userUpdateDTO.getGender() != null && !userUpdateDTO.getGender().equalsIgnoreCase(user.getGender().name())) {
            user.setGender(User.Gender.valueOf(userUpdateDTO.getGender().toUpperCase()));
            updatedFields.append("gender, ");
        }
        if (userUpdateDTO.getAddress() != null && !userUpdateDTO.getAddress().equals(user.getAddress())) {
            user.setAddress(userUpdateDTO.getAddress());
            updatedFields.append("address, ");
        }
        if (userUpdateDTO.getStatus() != null && !userUpdateDTO.getStatus().equals(user.getStatus().name())) {
            user.setStatus(User.Status.valueOf(userUpdateDTO.getStatus().toUpperCase()));
            updatedFields.append("status, ");
        }

        if (updatedFields.length() > 0) {
            updatedFields.setLength(updatedFields.length() - 2);
            userRepository.save(user);
            return updatedFields.toString() + " changed successfully";
        }

        return "No fields were updated";
    }

    
    

    public String deactivateUser(Integer id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return "User not found";
        }
    
        if (user.getStatus() == User.Status.INACTIVE) {
            return "User is already deactivated";
        }
    
        // Deactivate the user
        user.setStatus(User.Status.INACTIVE);
        userRepository.save(user);
        return "User deactivated successfully";
    }
    
    public String activateUser(Integer id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return "User not found";
        }
    
        if (user.getStatus() == User.Status.ACTIVE) {
            return "User is already active";
        }
    
        // Activate the user
        user.setStatus(User.Status.ACTIVE);
        userRepository.save(user);
        return "User activated successfully";
    }
}
