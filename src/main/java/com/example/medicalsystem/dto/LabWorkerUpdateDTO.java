package com.example.medicalsystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LabWorkerUpdateDTO {

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

    @JsonProperty("labDepartment")
    private String labDepartment;

    @Pattern(regexp = "^(ACTIVE|INACTIVE)$", message = "Status must be ACTIVE or INACTIVE")
    @JsonProperty("status")
    private String status;
}
