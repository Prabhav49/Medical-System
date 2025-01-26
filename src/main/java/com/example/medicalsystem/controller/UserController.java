package com.example.medicalsystem.controller;
import com.example.medicalsystem.dto.UserRequestDTO;
import com.example.medicalsystem.dto.UserResponseDTO;
import com.example.medicalsystem.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> registerUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO registeredUser = userService.registerUser(userRequestDTO);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }
}
