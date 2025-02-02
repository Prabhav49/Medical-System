package com.example.medicalsystem.dto;

import com.example.medicalsystem.entity.Doctor;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorResponseDTO {

    @JsonProperty("username")
    private String username;

    @JsonProperty("fullName")
    private String fullName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("gender")
    private String gender;

    @JsonProperty("specialization")
    private String specialization;

    @JsonProperty("experience")
    private String experience;

    @JsonProperty("status")
    private Doctor.Status status;
}
