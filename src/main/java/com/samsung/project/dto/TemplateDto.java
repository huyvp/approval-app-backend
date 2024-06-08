package com.samsung.project.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TemplateDto<T> {
    private String description;
    private String name;
    private boolean status;
    private int createUserId;
    @JsonFormat(pattern = "a hh:mm dd/MM/yyyy")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "a hh:mm dd/MM/yyyy")
    private LocalDateTime updatedAt;
    private int approver;
    private List<T> builderData;

}
