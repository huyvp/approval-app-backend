package com.samsung.project.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
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
    String status;
    List<T> requestFormData;
}
