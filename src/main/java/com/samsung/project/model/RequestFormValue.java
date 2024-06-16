package com.samsung.project.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.samsung.project.dto.FormBuilderDTO;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Data
public class RequestFormValue {
    int id;
    int requestId;
    String label;
    String placeholder;
    boolean required;
    String layout;
    String options;
    int templateId;
    int createUserId;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    LocalDateTime formatDateTime;
    String value;
    String type;
}