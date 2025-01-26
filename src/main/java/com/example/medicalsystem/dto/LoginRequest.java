package com.example.medicalsystem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank(message = "Email is requred")
    @Email(message = "email should be valid")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;
}
