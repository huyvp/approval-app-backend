package com.samsung.project.service;

import com.samsung.project.dto.TemplateDTO;
import com.samsung.project.model.ApproverTemplate;
import com.samsung.project.model.Template;
import com.samsung.project.model.TemplateFromBuilder;
import com.samsung.project.repo.ApproverTemplateRepo;
import com.samsung.project.repo.TemplateFormBuilderRepo;
import com.samsung.project.repo.TemplateRepo;
import com.samsung.project.response.TemplateDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class TemplateService {

    private final TemplateRepo templateRepo;
    private final ApproverTemplateRepo approverTemplateRepo;
    private final TemplateFormBuilderRepo templateFormBuilderRepo;

    @Autowired
    public TemplateService(TemplateRepo templateRepo, ApproverTemplateRepo approverTemplateRepo, TemplateFormBuilderRepo templateFormBuilderRepo) {
        this.templateRepo = templateRepo;
        this.approverTemplateRepo = approverTemplateRepo;
        this.templateFormBuilderRepo = templateFormBuilderRepo;
    }

    public void createTemplate(TemplateDTO<TemplateFromBuilder> templateDTO) {
        Template existingTemplate = templateRepo.findByName(templateDTO.getName());
        if (existingTemplate != null) {
            throw new RuntimeException("The template already exists!");
        }
        Template template = Template.builder()
                .description(templateDTO.getDescription())
                .name(templateDTO.getName())
                .status(templateDTO.isStatus())
                .createUserId(templateDTO.getCreateUserId())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        templateRepo.insert(template);

        Template templateSaved = templateRepo.findByName(templateDTO.getName());
        ApproverTemplate approverTemplate = ApproverTemplate.builder()
                .templateId(templateSaved.getId())
                .userId(templateDTO.getCreateUserId())
                .build();
        approverTemplateRepo.insert(approverTemplate);

        List<TemplateFromBuilder> templateFromBuilders = templateDTO.getBuilderData();
        for (TemplateFromBuilder item : templateFromBuilders) {
            item.setCreateUserId(templateDTO.getCreateUserId());
            item.setCreatedAt(LocalDateTime.now());
            item.setUpdatedAt(LocalDateTime.now());
            item.setTemplateId(templateSaved.getId());
            templateFormBuilderRepo.insertTempFormBuilder(item);
        }
    }

    public void updateTemplate(TemplateDTO<TemplateFromBuilder> templateDTO, int id) {
        Template existingTemplate = templateRepo.findById(id);
        if (existingTemplate == null) {
            throw new RuntimeException("This template does not exist!");
        }
        Template template = Template.builder()
                .description(templateDTO.getDescription())
                .name(templateDTO.getName())
                .status(templateDTO.isStatus())
                .updatedAt(LocalDateTime.now())
                .build();
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

        List<TemplateFromBuilder> templateFromBuilders = templateDTO.getBuilderData();
        templateFormBuilderRepo.deleteTempFormBuilder(id);
        for (TemplateFromBuilder item : templateFromBuilders) {
            item.setCreateUserId(templateDTO.getCreateUserId());
            item.setCreatedAt(LocalDateTime.now());
            item.setUpdatedAt(LocalDateTime.now());
            item.setTemplateId(id);
            templateFormBuilderRepo.insertTempFormBuilder(item);
        }
    }

    public void deleteTemplate(int id) {
        Template existingTemplate = templateRepo.findById(id);
        if (existingTemplate == null) {
            throw new RuntimeException("This template does not exist!");
        }
        ApproverTemplate existingApprover = approverTemplateRepo.findById(id);
        if (existingApprover == null) {
            throw new RuntimeException("The approver template does not exist!");
        }
        approverTemplateRepo.delete(id);
        templateFormBuilderRepo.deleteTempFormBuilder(id);
        templateRepo.delete(id);
    }

    public TemplateDTO<TemplateFromBuilder> getTemplateValue(int id) {
        TemplateDTO<TemplateFromBuilder> templateDto;
        templateDto = templateRepo.findValue(id);
        templateDto.setBuilderData(templateFormBuilderRepo.getTemplateFromBuilderById(id));
        return templateDto;
    }

    public List<TemplateDetail> getTemplateList() {
        return templateRepo.findListDetail();
    }

    public List<TemplateDetail> getTemplateListActive() {
        return templateRepo.findListDetailActive();
    }

    public TemplateDetail getTemplateDetail(int id) {
        TemplateDetail templateDetail = templateRepo.findDetail(id);
        if (templateDetail == null) {
            throw new RuntimeException("This template does not exist!");
        }
        return templateDetail;
    }
}
