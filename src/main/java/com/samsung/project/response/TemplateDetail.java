package com.samsung.project.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TemplateDetail {
    private String id;
    private String name;
    private String description;
    private String userName;
    @JsonFormat(pattern = "hh:mm:ss dd/MM/yyyy",timezone = "Asia/Bangkok")
    private LocalDateTime updatedAt;
    private boolean status;
    private int createUserId;
}
