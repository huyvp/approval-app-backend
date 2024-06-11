package com.samsung.project.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static com.samsung.project.constant.Constants.Pattern.TIME;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Request {
    private int id;
    private int resourceId;
    private String purpose;
    private String note;
    private long createUserId;
    @JsonFormat(pattern = TIME)
    private LocalDateTime createdAt;
    @JsonFormat(pattern = TIME)
    private LocalDateTime updatedAt;
    private String status;
}
