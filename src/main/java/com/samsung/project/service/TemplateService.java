package com.samsung.project.service;

import com.samsung.project.dto.FormBuilderDTO;
import com.samsung.project.dto.TemplateDTO;
import com.samsung.project.exception.ApprovalException;
import com.samsung.project.exception.ErrorCode;
import com.samsung.project.mapper.TemplateMapper;
import com.samsung.project.model.ApproverTemplate;
import com.samsung.project.model.Template;
import com.samsung.project.model.TemplateFromBuilder;
import com.samsung.project.repo.ApproverTemplateRepo;
import com.samsung.project.repo.TemplateFormBuilderRepo;
import com.samsung.project.repo.TemplateRepo;
import com.samsung.project.response.TemplateDetailResponse;
import com.samsung.project.response.TemplateValueResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class TemplateService {
    TemplateRepo templateRepo;
    ApproverTemplateRepo approverTemplateRepo;
    TemplateFormBuilderRepo templateFormBuilderRepo;
    TemplateMapper templateMapper;

    public void createTemplate(TemplateDTO templateDTO) {
        Template existingTemplate = templateRepo.findByName(templateDTO.getName());
        if (existingTemplate != null) {
            throw new RuntimeException("The template already exists!");
        }
        Template template = templateMapper.toTemplate(templateDTO);
        template.setCreatedAt(LocalDateTime.now());
        template.setUpdatedAt(LocalDateTime.now());
        templateRepo.insert(template);

        Template templateSaved = templateRepo.findByName(templateDTO.getName());
        ApproverTemplate approverTemplate = ApproverTemplate.builder()
                .templateId(templateSaved.getId())
                .userId(templateDTO.getCreateUserId())
                .build();
        approverTemplateRepo.insert(approverTemplate);

        List<FormBuilderDTO> builderData = templateDTO.getBuilderData();

        for (FormBuilderDTO item : builderData) {
            TemplateFromBuilder formBuilder = templateMapper.toTemplateFromBuilder(item);
            formBuilder.setCreatedAt(LocalDateTime.now());
            formBuilder.setUpdatedAt(LocalDateTime.now());
            formBuilder.setTemplateId(templateSaved.getId());
            templateFormBuilderRepo.insert(formBuilder);
        }
    }

    public void updateTemplate(TemplateDTO templateDTO, int id) {
        Template existingTemplate = templateRepo.findById(id);
        if (existingTemplate == null) {
            throw new RuntimeException("This template does not exist!");
        }
        Template template = templateMapper.toTemplate(templateDTO);
        template.setUpdatedAt(LocalDateTime.now());
        templateRepo.update(template);

        ApproverTemplate existingApprover = approverTemplateRepo.findById(id);
        if (existingApprover == null) {
            throw new RuntimeException("The approver template does not exist!");
        }
        if (existingApprover.getUserId() != templateDTO.getCreateUserId()) {
            ApproverTemplate approver = ApproverTemplate.builder()
                    .userId(templateDTO.getApprover())
                    .build();
            approverTemplateRepo.update(approver);
        }
        templateFormBuilderRepo.delete(id);

        List<FormBuilderDTO> builderData = templateDTO.getBuilderData();
        for (FormBuilderDTO item : builderData) {
            TemplateFromBuilder formBuilder = templateMapper.toTemplateFromBuilder(item);
            formBuilder.setCreateUserId(templateDTO.getCreateUserId());
            formBuilder.setUpdatedAt(LocalDateTime.now());
            formBuilder.setTemplateId(id);
            templateFormBuilderRepo.insert(formBuilder);
        }
    }

    public void deleteTemplate(int id) {
        Template existingTemplate = templateRepo.findById(id);
        if (existingTemplate == null) {
            throw new ApprovalException(ErrorCode.APP303);
        }
        ApproverTemplate existingApprover = approverTemplateRepo.findById(id);
        if (existingApprover == null) {
            throw new ApprovalException(ErrorCode.APP301);
        }
        approverTemplateRepo.delete(id);
        templateFormBuilderRepo.delete(id);
        templateRepo.delete(id);
    }

    public TemplateValueResponse getTemplateValue(int id) {
        TemplateValueResponse templateValueResponse = templateRepo.findValue(id);
        templateValueResponse.setBuilderData(templateFormBuilderRepo.findById(id));
        return templateValueResponse;
    }

    public List<TemplateDetailResponse> getTemplateList() {
        return templateRepo.findListDetail();
    }

    public List<TemplateDetailResponse> getTemplateListActive() {
        return templateRepo.findListDetailActive();
    }

    public TemplateDetailResponse getTemplateDetail(int id) {
        TemplateDetailResponse templateDetailResponse = templateRepo.findDetail(id);
        if (templateDetailResponse == null) {
            throw new ApprovalException(ErrorCode.APP303);
        }
        return templateDetailResponse;
    }
}
