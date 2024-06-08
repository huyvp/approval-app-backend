package com.samsung.project.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TemplateDetail {
    private String id;
    private String name;
    private String description;
    private String userName;
    @JsonFormat(pattern = "a hh:mm, dd/MM/yyyy")
    private LocalDateTime updatedAt;
    private boolean status;
    private int createUserId;
}
