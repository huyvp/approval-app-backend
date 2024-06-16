package com.samsung.project.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Data
public class FormBuilderDTO {
    int requestId;
    String label;
    String placeholder;
    boolean required;
    String layout;
    String options;
    int templateId;
    int createUserId;
    LocalDateTime formatDateTime;
    String value;
    String type;
}
