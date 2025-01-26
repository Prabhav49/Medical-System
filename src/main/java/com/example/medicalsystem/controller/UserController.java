package com.example.medicalsystem.controller;
import com.example.medicalsystem.dto.UserRequestDTO;
import com.example.medicalsystem.dto.UserResponseDTO;
import com.example.medicalsystem.dto.UserUpdateDTO;
import com.example.medicalsystem.service.UserService;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
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
    @GetMapping("/getUserByUsername/{username}")
    public ResponseEntity<UserResponseDTO> getUserByUsername(@PathVariable String username,HttpServletRequest httpRequest) {
        UserResponseDTO user = userService.getUserInfo(username);
        if(user == null) return ResponseEntity.status(404).body(null);
        return ResponseEntity.ok(user);
    }

    @PatchMapping("/update/{id}")
    public String updateUserDetails(@PathVariable Integer id, 
        @Valid @RequestBody UserUpdateDTO userUpdateDTO) {
        return  userService.updateUserDetails(id, userUpdateDTO);
    }

    @PutMapping("/deactivate/{id}")
    public String deactivateUser(@PathVariable Integer id) {
        return userService.deactivateUser(id);
    }
    @PutMapping("/activate/{id}")
    public String activateUser(@PathVariable Integer id) {
        return userService.activateUser(id);
    }
    
}
