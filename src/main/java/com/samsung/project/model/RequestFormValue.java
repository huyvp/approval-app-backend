package com.samsung.project.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.samsung.project.dto.FormBuilderDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RequestFormValue {
    private int id;
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

    public static TemplateFromBuilder fromFormBuilderDTO(FormBuilderDTO formBuilderDTO) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.convertValue(formBuilderDTO, TemplateFromBuilder.class);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }
}