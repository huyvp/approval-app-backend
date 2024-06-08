package com.samsung.project.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Request {
    private int id;
    private int resourceId;
    private String purpose;
    private String note;
    private long createUserId;
    @JsonFormat(pattern = "a hh:mm dd/MM/yyyy")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "a hh:mm dd/MM/yyyy")
    private LocalDateTime updatedAt;
    private String status;
}
