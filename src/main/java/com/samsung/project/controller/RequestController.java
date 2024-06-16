package com.samsung.project.controller;

import com.samsung.project.dto.RequestDTO;
import com.samsung.project.handler.ResponseHandler;
import com.samsung.project.model.RequestApproval;
import com.samsung.project.model.RequestFormValue;
import com.samsung.project.service.RequestService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@PreAuthorize("hasAnyRole('ADMIN','USER')")
@RequestMapping("${api.prefix}/requests")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RequestController {
    RequestService requestService;

    @PostMapping
    public ResponseEntity<?> addRequest(@RequestBody RequestDTO<RequestFormValue> requestDTO) {
        requestService.createRequest(requestDTO);
        return ResponseHandler.execute(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRequest(@RequestBody RequestDTO<RequestFormValue> requestDto, @PathVariable int id) {
        requestService.updateRequest(requestDto, id);
        return ResponseHandler.execute(null);
    }

    @PutMapping("/approval/{id}")
    public ResponseEntity<?> updateRequestApproval(@RequestBody RequestApproval requestApproval, @PathVariable int id) {
        requestService.updateWhenApproval(requestApproval, id);
        return ResponseHandler.execute(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRequest(@PathVariable int id) {
        requestService.deleteRequest(id);
        return ResponseHandler.execute(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRequestValue(@PathVariable int id) {
        return ResponseHandler.execute(requestService.getRequestValue(id));
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAllRequest() {
        return ResponseEntity.ok((this.requestService.getAllRequest()));
    }

    @GetMapping("/myRequest/{id}")
    public ResponseEntity<?> getMyRequestDetail(@PathVariable int id) {
        return ResponseHandler.execute(requestService.getRequestOfUser(id));
    }

    @GetMapping("/process/{id}")
    public ResponseEntity<?> getRequestNeedProcess(@PathVariable int id) {
        return ResponseHandler.execute(requestService.getRequestNeedProcess(id));
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getRequestById(@PathVariable int id) {
        return ResponseHandler.execute(requestService.getRequestById(id));
    }

    @GetMapping("/detail/note/{id}")
    public ResponseEntity<?> getNoteApprove(@PathVariable int id) {
        return ResponseHandler.execute(requestService.getNoteApprove(id));
    }
}
