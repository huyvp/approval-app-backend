package com.samsung.project.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.samsung.project.model.TemplateFromBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import static com.samsung.project.constant.Constants.Pattern.TIME;

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
    @JsonFormat(pattern = TIME)
    private LocalDateTime createdAt;
    @JsonFormat(pattern = TIME)
    private LocalDateTime updatedAt;
    private int approver;
    private List<TemplateFromBuilder> builderData;
}
