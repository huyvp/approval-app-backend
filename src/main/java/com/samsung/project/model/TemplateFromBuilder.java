package com.samsung.project.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.samsung.project.dto.FormBuilderDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static com.samsung.project.constant.Constants.Pattern.TIME;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class TemplateFromBuilder {
    private int id;
    private String label;
    private String name;
    private String placeholder;
    private boolean required;
    private String layout;
    private String options;
    private int templateId;
    private int createUserId;
    @JsonFormat(pattern = TIME)
    private LocalDateTime createdAt;
    @JsonFormat(pattern = TIME)
    private LocalDateTime updatedAt;
    private LocalDateTime formatDateTime;
    private String value;
    private String type;

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
