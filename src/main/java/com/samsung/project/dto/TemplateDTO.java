package com.samsung.project.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import static com.samsung.project.constant.Constants.Pattern.TIME;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TemplateDTO {
    private String description;
    private String name;
    private boolean status;
    private int createUserId;
    @JsonFormat(pattern = TIME)
    private LocalDateTime createdAt;
    @JsonFormat(pattern = TIME)
    private LocalDateTime updatedAt;
    private int approver;
    private List<FormBuilderDTO> builderData;
}
