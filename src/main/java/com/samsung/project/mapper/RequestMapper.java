package com.samsung.project.mapper;

import com.samsung.project.dto.FormBuilderDTO;
import com.samsung.project.dto.RequestDTO;
import com.samsung.project.model.Request;
import com.samsung.project.model.RequestFormValue;
import com.samsung.project.model.TemplateFromBuilder;
import org.mapstruct.Mapper;

@Mapper
public interface RequestMapper {
    Request toRequest(RequestDTO requestDTO);
    RequestFormValue toTemplateFromBuilder(TemplateFromBuilder templateFromBuilder);
}
