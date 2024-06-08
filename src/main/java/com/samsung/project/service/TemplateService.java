package com.samsung.project.service;

import com.samsung.project.dto.TemplateDTO;
import com.samsung.project.dto.TemplateDetail;
import com.samsung.project.model.ApproverTemplate;
import com.samsung.project.model.Template;
import com.samsung.project.model.TemplateFromBuilder;
import com.samsung.project.repo.ApproverTemplateRepo;
import com.samsung.project.repo.TemplateFormBuilderRepo;
import com.samsung.project.repo.TemplateRepo;
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

    public boolean createTemplate(TemplateDTO<TemplateFromBuilder> templateDTO) {
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
        templateRepo.insertTemplate(template);

        Template templateSaved = templateRepo.findByName(templateDTO.getName());
        ApproverTemplate approverTemplate = ApproverTemplate.builder()
                .templateId(templateSaved.getId())
                .userId(templateDTO.getCreateUserId())
                .build();
        approverTemplateRepo.insertApprover(approverTemplate);

        List<TemplateFromBuilder> templateFromBuilders = templateDTO.getBuilderData();
        for (TemplateFromBuilder item : templateFromBuilders) {
            item.setCreateUserId(templateDTO.getCreateUserId());
            item.setCreatedAt(LocalDateTime.now());
            item.setUpdatedAt(LocalDateTime.now());
            item.setTemplateId(templateSaved.getId());
            templateFormBuilderRepo.insertTempFormBuilder(item);
        }
        return true;
    }

    public boolean updateTemplate(TemplateDTO<TemplateFromBuilder> templateDTO, int id) {
        Template existingTemplate = templateRepo.findById(id);
        if (existingTemplate == null) {
            throw new RuntimeException("The template does not exist!");
        }
        Template template = Template.builder()
                .description(templateDTO.getDescription())
                .name(templateDTO.getName())
                .status(templateDTO.isStatus())
                .updatedAt(LocalDateTime.now())
                .build();
        templateRepo.updateTemplate(template);

        ApproverTemplate approverTemplate = new ApproverTemplate();
        approverTemplate.setTemplateId(id);
        approverTemplate.setUserId(templateDTO.getApprover());
        approverTemplateRepo.updateApprover(approverTemplate);

        List<TemplateFromBuilder> templateFromBuilders = templateDTO.getBuilderData();
        templateFormBuilderRepo.deleteTempFormBuilder(id);
        for (TemplateFromBuilder item : templateFromBuilders) {
            item.setCreateUserId(templateDTO.getCreateUserId());
            item.setCreatedAt(LocalDateTime.now());
            item.setUpdatedAt(LocalDateTime.now());
            item.setTemplateId(id);
            templateFormBuilderRepo.insertTempFormBuilder(item);
        }
        return true;
    }

    public boolean deleteTemplate(int id) {
        approverTemplateRepo.deleteApprover(id);
        templateFormBuilderRepo.deleteTempFormBuilder(id);
        templateRepo.deleteTemplate(id);
        return true;
    }

    public TemplateDTO<TemplateFromBuilder> getTemplateDto(int id) {
        TemplateDTO<TemplateFromBuilder> templateDto;
        templateDto = templateRepo.getTemplateDto(id);
        templateDto.setBuilderData(templateFormBuilderRepo.getTemplateFromBuilderById(id));
        return templateDto;
    }

    public List<TemplateDetail> getTemplateList() {
        return templateRepo.getTemplateList();
    }

    public List<TemplateDetail> getTemplateListActive() {
        return templateRepo.getTemplateListActive();
    }

    public TemplateDetail getTemplateDetail(int id) {
        return templateRepo.getTemplateDetail(id);
    }
}
