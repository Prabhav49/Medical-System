package com.example.medicalsystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.*;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserUpdateDTO {
    @JsonProperty("username")
    private String username;

    @JsonProperty("fullName")
    private String fullName;

    @Size(min = 6, max = 12, message = "Password must be between 6 and 12 characters")
    @JsonProperty("password")
    private String password;

    @JsonProperty("email")
    private String email;

    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Phone number must be valid")
    @JsonProperty("phone")
    private String phone;
}
