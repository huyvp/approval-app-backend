package com.samsung.project.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

import static com.samsung.project.constant.Constants.Pattern.TIME;

@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class TemplateDTO {
    String description;
    String name;
    boolean status;
    int createUserId;
    @JsonFormat(pattern = TIME)
    LocalDateTime createdAt;
    @JsonFormat(pattern = TIME)
    LocalDateTime updatedAt;
    int approver;
    List<FormBuilderDTO> builderData;
}
