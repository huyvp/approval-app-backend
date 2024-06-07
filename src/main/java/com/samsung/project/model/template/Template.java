package com.samsung.project.model.template;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Template {
    private int id;
    private String description;
    private String name;
    private boolean status;
    private int createUserId;
    @JsonFormat(pattern = "a hh:mm, dd/MM/yyyy")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "a hh:mm, dd/MM/yyyy")
    private LocalDateTime updatedAt;
}
