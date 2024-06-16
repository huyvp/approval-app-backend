package com.samsung.project.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

import static com.samsung.project.constant.Constants.Pattern.TIME;

@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class TemplateDTO {
    @NotBlank(message = "VALID107")
    String description;
    @NotBlank(message = "VALID108")
    String name;
    boolean status;
    @Min(value = 1, message = "VALID100")
    int createUserId;
    @Min(value = 1, message = "VALID100")
    int approver;
    List<FormBuilderDTO> builderData;
}
