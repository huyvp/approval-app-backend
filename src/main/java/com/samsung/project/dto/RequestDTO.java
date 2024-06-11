package com.samsung.project.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

import static com.samsung.project.constant.Constants.Pattern.TIME;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestDTO<T> {
    @Min(value = 1, message = "INVALID_ID")
    private int id;
    @Min(value = 1, message = "INVALID_ID")
    private int resourceId;
    @Size(min = 1, max = 20, message = "INVALID_REQUEST_PURPOSE")
    private String purpose;
    @Size(min = 1, max = 200, message = "INVALID_REQUEST_NOTE")
    private String note;
    @Min(value = 1, message = "INVALID_ID")
    private long createUserId;
    @JsonFormat(pattern = TIME)
    private LocalDateTime createdAt;
    @JsonFormat(pattern = TIME)
    private LocalDateTime updatedAt;
    private String status;
    private List<T> requestFormData;
}
