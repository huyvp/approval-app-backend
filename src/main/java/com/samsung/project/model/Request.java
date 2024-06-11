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
public class Request {
    int id;
    int resourceId;
    String purpose;
    String note;
    long createUserId;
    @JsonFormat(pattern = TIME)
    LocalDateTime createdAt;
    @JsonFormat(pattern = TIME)
    LocalDateTime updatedAt;
    String status;
}
