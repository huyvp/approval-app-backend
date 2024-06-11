package com.samsung.project.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Data
public class Template {
    int id;
    String description;
    String name;
    boolean status;
    int createUserId;
    @JsonFormat(pattern = "a hh:mm, dd/MM/yyyy")
    LocalDateTime createdAt;
    @JsonFormat(pattern = "a hh:mm, dd/MM/yyyy")
    LocalDateTime updatedAt;
}
