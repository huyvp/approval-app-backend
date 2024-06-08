package com.samsung.project.service;

import com.samsung.project.dto.TemplateDto;
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


    public boolean insertTemplate(TemplateDto<TemplateFromBuilder> templateDto) {
        List<Template> templates = templateRepo.getAllTemplate();
        boolean result = false;
        for (Template temp : templates) {
            result = !temp.getName().equals(templateDto.getName());
        }
        if (result || templates.isEmpty()) {
            // insert template
            Template template = new Template();
            template.setDescription(templateDto.getDescription());
            template.setName(templateDto.getName());
            template.setStatus(templateDto.isStatus());
            template.setCreateUserId(templateDto.getCreateUserId());
            template.setCreatedAt(LocalDateTime.now());
            template.setUpdatedAt(LocalDateTime.now());
            templateRepo.addTemplate(template);

            // insert approver template
            ApproverTemplate approverTemplate = new ApproverTemplate();
            int tempLastValue = templateRepo.getTemplateLastValue();
            approverTemplate.setTemplateId(tempLastValue);
            approverTemplate.setUserId(templateDto.getApprover());
            approverTemplateRepo.insertApprover(approverTemplate);

            // insert template form builder
            List<TemplateFromBuilder> templateFromBuilders = templateDto.getBuilderData();
            for (TemplateFromBuilder item : templateFromBuilders) {
                item.setCreateUserId(templateDto.getCreateUserId());
                item.setCreatedAt(LocalDateTime.now());
                item.setUpdatedAt(LocalDateTime.now());
                item.setTemplateId(tempLastValue);
                templateFormBuilderRepo.insertTempFormBuilder(item);
            }
            result = true;
        }
        return result;
    }

    public boolean updateTemplate(TemplateDto<TemplateFromBuilder> templateDto, int id) {
        List<Template> templates = templateRepo.getAllTemplate();
        boolean result = true;
        while (result) {
            // update template
            Template template = new Template();
            template.setId(id);
            template.setDescription(templateDto.getDescription());
            if (templates.size() > 1) {
                for (Template temp : templates) {
                    if (temp.getId() == id) {
                        templates.remove(temp);
                        break;
                    }
                }
                for (Template temp : templates) {
                    if (temp.getName().equals(templateDto.getName())) result = false;
                    else template.setName(templateDto.getName());
                }
            } else template.setName(templateDto.getName());
            template.setStatus(templateDto.isStatus());
            template.setCreateUserId(templateDto.getCreateUserId());
            template.setUpdatedAt(LocalDateTime.now());
            templateRepo.updateTemplate(template);

            // insert approver template
            ApproverTemplate approverTemplate = new ApproverTemplate();
            approverTemplate.setTemplateId(id);
            approverTemplate.setUserId(templateDto.getApprover());
            approverTemplateRepo.updateApprover(approverTemplate);

            // insert template form builder
            List<TemplateFromBuilder> templateFromBuilders = templateDto.getBuilderData();
            templateFormBuilderRepo.deleteTempFormBuilder(id);
            for (TemplateFromBuilder item : templateFromBuilders) {
                item.setCreateUserId(templateDto.getCreateUserId());
                item.setCreatedAt(LocalDateTime.now());
                item.setUpdatedAt(LocalDateTime.now());
                item.setTemplateId(id);
                templateFormBuilderRepo.insertTempFormBuilder(item);
            }
            break;
        }
        return result;
    }

    public boolean deleteTemplate(int id) {
        approverTemplateRepo.deleteApprover(id);
        templateFormBuilderRepo.deleteTempFormBuilder(id);
        templateRepo.deleteTemplate(id);
        return true;
    }

    public TemplateDto<TemplateFromBuilder> getTemplateDto(int id) {
        TemplateDto<TemplateFromBuilder> templateDto;
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
