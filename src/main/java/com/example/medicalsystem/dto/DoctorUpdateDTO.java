package com.example.medicalsystem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorUpdateDTO {

    @JsonProperty("username")
    private String username;

    @JsonProperty("fullName")
    private String fullName;

    @Size(min = 6, max = 12, message = "Password must be between 6 and 12 characters")
    @JsonProperty("password")
    private String password;

    @Email(message = "Invalid email format")
    @JsonProperty("email")
    private String email;

    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Phone number must be valid")
    @JsonProperty("phone")
    private String phone;

    @JsonProperty("dateOfBirth")
    @JsonFormat(pattern = "dd MM yyyy")
    private LocalDate dateOfBirth;

    @Pattern(regexp = "^(MALE|FEMALE|OTHER)$", message = "Gender must be MALE, FEMALE, or OTHER")
    @JsonProperty("gender")
    private String gender;

    @JsonProperty("address")
    private String address;

    @NotNull(message = "Specialization is required")
    @JsonProperty("specialization")
    private String specialization;

    @NotNull(message = "Experience is required")
    @JsonProperty("experience")
    private String experience;

    @Pattern(regexp = "^(ACTIVE|INACTIVE)$", message = "Status must be ACTIVE or INACTIVE")
    @JsonProperty("status")
    private String status;
}
