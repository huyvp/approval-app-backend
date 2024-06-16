package com.samsung.project.mapper;

import com.samsung.project.dto.FormBuilderDTO;
import com.samsung.project.dto.TemplateDTO;
import com.samsung.project.model.Template;
import com.samsung.project.model.TemplateFromBuilder;
import org.mapstruct.Mapper;

@Mapper
public interface TemplateMapper {
    Template toTemplate(TemplateDTO templateDTO);
    TemplateFromBuilder toTemplateFromBuilder(FormBuilderDTO formBuilderDTO);
}
