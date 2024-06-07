package com.samsung.project.dto.template;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.NumberSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

//    private int id;
//    private String description;
//    private int tempFormId;
//    private String name;
//    private boolean status;
//    private int createUserId;
//    private LocalDateTime createdAt;
//    private LocalDateTime updatedAt;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TemplateDto<T> {
    private String description;
    private String name;
    private boolean status;
    private int createUserId;
    @JsonFormat(pattern = "a hh:mm dd/MM/yyyy")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "a hh:mm dd/MM/yyyy")
    private LocalDateTime updatedAt;
    private int approver;
    private T[] builderData;

}
