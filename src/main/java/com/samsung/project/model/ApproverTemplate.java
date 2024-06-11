package com.samsung.project.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Data
public class ApproverTemplate {
    int userId;
    int templateId;
}
