package com.example.medicalsystem.dto;

import com.example.medicalsystem.entity.LabWorker;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LabWorkerResponseDTO {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("fullName")
    private String fullName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("labDepartment")
    private String labDepartment;

    @JsonProperty("status")
    private LabWorker.Status status;
}
