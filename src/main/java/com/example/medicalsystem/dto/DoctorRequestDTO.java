package com.example.medicalsystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorRequestDTO {

    @NotNull(message = "Username is required")
    @NotEmpty(message = "Username should not be empty")
    @NotBlank(message = "Username should not be blank")
    @JsonProperty("username")
    private String username;

    @NotNull(message = "Full Name is required")
    @NotEmpty(message = "Full Name should not be empty")
    @NotBlank(message = "Full Name should not be blank")
    @JsonProperty("fullName")
    private String fullName;

    @NotNull(message = "Password is required")
    @NotEmpty(message = "Password should not be empty")
    @NotBlank(message = "Password should not be blank")
    @Size(min = 6, max = 12, message = "Password must be between 6 and 12 characters")
    @JsonProperty("password")
    private String password;

    @NotNull(message = "Email is required")
    @Email(message = "Email must be in the correct format")
    @JsonProperty("email")
    private String email;

    @NotNull(message = "Phone number is required")
    @NotEmpty(message = "Phone number should not be empty")
    @NotBlank(message = "Phone number should not be blank")
    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Phone number must be valid")
    @JsonProperty("phone")
    private String phone;

    @NotNull(message = "Gender is required")
    @JsonProperty("gender")
    private String gender;

    @NotNull(message = "Specialization is required")
    @NotEmpty(message = "Specialization should not be empty")
    @NotBlank(message = "Specialization should not be blank")
    @JsonProperty("specialization")
    private String specialization;

    @NotNull(message = "Experience is required")
    @NotEmpty(message = "Experience should not be empty")
    @NotBlank(message = "Experience should not be blank")
    @JsonProperty("experience")
    private String experience;

    @NotNull(message = "Status is required")
    @JsonProperty("status")
    private String status;
}
