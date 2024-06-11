package com.samsung.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class FormBuilderDTO {
    private int requestId;
    private String label;
    private String placeholder;
    private boolean required;
    private String layout;
    private String options;
    private int templateId;
    private int createUserId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime formatDateTime;
    private String value;
    private String type;
}
