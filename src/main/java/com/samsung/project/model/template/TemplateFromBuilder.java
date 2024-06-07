package com.samsung.project.model.template;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TemplateFromBuilder {
    private int id;
    private String label;
    private String name;
    private  String placeholder;
    private boolean required;
    private String layout;
    private String options;
    private int templateId;
    private int createUserId;
    @JsonFormat(pattern = "hh:mm a, dd/MM/yyyy")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "hh:mm a, dd/MM/yyyy")
    private LocalDateTime updatedAt;
    private LocalDateTime formatDateTime;
    private String value;
    private String type;
}
