package com.samsung.project.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.samsung.project.dto.FormBuilderDTO;
import com.samsung.project.model.TemplateFromBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TemplateValueResponse {
    private int id;
    private String description;
    private String name;
    private boolean status;
    private int createUserId;
    @JsonFormat(pattern = "a hh:mm dd/MM/yyyy")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "a hh:mm dd/MM/yyyy")
    private LocalDateTime updatedAt;
    private int approver;
    private List<TemplateFromBuilder> builderData;
}
