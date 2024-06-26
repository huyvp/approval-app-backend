package com.samsung.project.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDetailResponse {
    private int id;
    private String requests;
    private String description;
    private String approver;
    @JsonFormat(pattern = "a hh:mm dd/MM/yyyy")
    private LocalDateTime updatedAt;
    private String status;
}
