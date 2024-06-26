package com.samsung.project.controller;

import com.samsung.project.dto.TemplateDTO;
import com.samsung.project.handler.ResponseHandler;
import com.samsung.project.mapper.TemplateMapper;
import com.samsung.project.service.TemplateService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping(value = "${api.prefix}/template")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TemplateController {
    TemplateService templateService;

    @PostMapping
    public ResponseEntity<?> createTemplate(@RequestBody TemplateDTO templateDto) {
        templateService.createTemplate(templateDto);
        return ResponseHandler.execute(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTemplate(@RequestBody TemplateDTO templateDto, @PathVariable int id) {
        templateService.updateTemplate(templateDto, id);
        return ResponseHandler.execute(null);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTemplate(@PathVariable int id) {
        templateService.deleteTemplate(id);
        return ResponseHandler.execute(null);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getTemplateValue(@PathVariable int id) {
        return ResponseEntity.ok(templateService.getTemplateValue(id));
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/list")
    public ResponseEntity<?> getTemplateList() {
        return ResponseEntity.ok(templateService.getTemplateList());
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/list/active")
    public ResponseEntity<?> getTemplateListActive() {
        return ResponseEntity.ok(templateService.getTemplateListActive());
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getTemplateDetail(@PathVariable int id) {
        return ResponseEntity.ok(templateService.getTemplateDetail(id));
    }
}
