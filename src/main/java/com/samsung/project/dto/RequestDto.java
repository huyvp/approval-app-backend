package com.samsung.project.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestDto<T> {
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
    private T[] requestFormValueData;
}
