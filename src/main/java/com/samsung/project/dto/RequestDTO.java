package com.samsung.project.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

import static com.samsung.project.constant.Constants.Pattern.TIME;

@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class RequestDTO<T> {
    @Min(value = 1, message = "VALID100")
    int id;
    @Min(value = 1, message = "VALID100")
    int resourceId;
    @Size(min = 1, max = 20, message = "VALID101")
    String purpose;
    @Size(min = 1, max = 200, message = "VALID102")
    String note;
    @Min(value = 1, message = "VALID100")
    long createUserId;
    @JsonFormat(pattern = TIME)
    LocalDateTime createdAt;
    @JsonFormat(pattern = TIME)
    LocalDateTime updatedAt;
    String status;
    List<T> requestFormData;
}
