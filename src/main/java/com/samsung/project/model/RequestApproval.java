package com.samsung.project.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

import static com.samsung.project.constant.Constants.Pattern.TIME;

@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Data
public class RequestApproval {
    int userId;
    int requestId;
    String approvalStatus;
    @JsonFormat(pattern = TIME)
    LocalDateTime approvalTime;
    String comment;
}