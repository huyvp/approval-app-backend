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
public class RequestApproval {
    private int userId;
    private int requestId;
    private String approvalStatus;
    @JsonFormat(pattern = TIME)
    private LocalDateTime approvalTime;
    private String comment;
}