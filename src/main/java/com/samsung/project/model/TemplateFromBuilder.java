package com.samsung.project.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.samsung.project.dto.FormBuilderDTO;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

import static com.samsung.project.constant.Constants.Pattern.TIME;

@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Data
public class TemplateFromBuilder {
    int id;
    String label;
    String name;
    String placeholder;
    boolean required;
    String layout;
    String options;
    int templateId;
    int createUserId;
    @JsonFormat(pattern = TIME)
    LocalDateTime createdAt;
    @JsonFormat(pattern = TIME)
    LocalDateTime updatedAt;
    LocalDateTime formatDateTime;
    String value;
    String type;

    public static TemplateFromBuilder fromFormBuilderDTO(FormBuilderDTO formBuilderDTO){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode jsonNode = objectMapper.valueToTree(formBuilderDTO);
            jsonNode.remove("requestId");
            return objectMapper.treeToValue(jsonNode, TemplateFromBuilder.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
