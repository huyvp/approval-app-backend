package com.samsung.project.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDetail {
    private int id;
    private String requests;
    private String description;
    private String approver;
    @JsonFormat(pattern = "a hh:mm dd/MM/yyyy")
    private LocalDateTime updatedAt;
    private String status;
}
