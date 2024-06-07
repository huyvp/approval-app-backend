package com.samsung.project.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestFormValue {
    private int id;
    private String label;
    private String placeholder;
    private boolean required;
    private String layout;
    private String options;
    private int requestId;
    private int templateId;
    private int createUserId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime formatDateTime;
    private String value;
    private String type;
}