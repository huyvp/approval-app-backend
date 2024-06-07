package com.samsung.project.controller;

import com.samsung.project.dto.request.RequestDto;
import com.samsung.project.model.request.RequestApproval;
import com.samsung.project.model.request.RequestFormValue;
import com.samsung.project.service.request.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@PreAuthorize("hasAnyRole('ADMIN','USER')")
@RequestMapping("/api/v1/requests")
public class RequestController {
    private final RequestService requestService;

    @Autowired
    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping
    public ResponseEntity<?> addRequest(@RequestBody RequestDto<RequestFormValue> RequestDto) {
        if (requestService.addRequest(RequestDto) > 0) {
            return ResponseEntity.ok("Created success");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Create Failed");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRequest(@RequestBody RequestDto<RequestFormValue> requestDto, @PathVariable int id) {
        if (requestService.updateRequest(requestDto, id) > 0) {
            return ResponseEntity.ok("update success");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Update failed");
        }
    }

    @PutMapping("/approval/{id}")
    public ResponseEntity<?> updateRequestApproval(@RequestBody RequestApproval requestApproval, @PathVariable int id) {
        if (requestService.updateWhenApproval(requestApproval, id) > 0) {
            return ResponseEntity.ok("Approved success");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Approved failed");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRequest(@PathVariable int id) {
        if (requestService.deleteRequest(id) > 0) {
            return ResponseEntity.ok("Delete success");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Delete Failed");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRequestDTO(@PathVariable int id) {
        return ResponseEntity.ok(requestService.getRequestDto(id));
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAllRequest() {
        return ResponseEntity.ok((this.requestService.getAllRequest()));
    }

    @GetMapping("/myRequest/{id}")
    public ResponseEntity<?> getMyRequestDetail(@PathVariable int id) {
        return ResponseEntity.ok(requestService.getMyRequestDetail(id));
    }

    @GetMapping("/process/{id}")
    public ResponseEntity<?> getRequestNeedProcess(@PathVariable int id) {
        return ResponseEntity.ok(requestService.getRequestNeedProcess(id));
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getRequestById(@PathVariable int id) {
        return ResponseEntity.ok(requestService.getRequestById(id));
    }

    @GetMapping("/detail/note/{id}")
    public ResponseEntity<?> getNoteApprove(@PathVariable int id) {
        return ResponseEntity.ok(requestService.getNoteApprove(id));
    }
}
