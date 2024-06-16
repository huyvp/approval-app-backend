package com.samsung.project.service;

import com.samsung.project.dto.RequestDTO;
import com.samsung.project.mapper.RequestMapper;
import com.samsung.project.model.*;
import com.samsung.project.repo.*;
import com.samsung.project.response.RequestDetailResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static com.samsung.project.model.ApproveStatus.AWAITING;

@Transactional
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RequestService {
    RequestRepo requestRepo;
    RequestMapper requestMapper;
    RequestApprovalRepo requestApprovalRepo;
    RequestFormValueRepo requestFormValueRepo;
    TemplateFormBuilderRepo templateFormBuilderRepo;
    ApproverTemplateRepo approverTemplateRepo;

    public void createRequest(RequestDTO<RequestFormValue> requestDTO) {
        try {
            Request request = requestMapper.toRequest(requestDTO);
            request.setCreatedAt(LocalDateTime.now());
            request.setUpdatedAt(LocalDateTime.now());
            requestRepo.insert(request);

            ApproverTemplate approverTemplate = approverTemplateRepo.findById(requestDTO.getResourceId());
            if (approverTemplate == null) {
                throw new RuntimeException("Approver not found");
            }
            int lastRequestId = requestRepo.lastRequestId();
            RequestApproval requestApproval = RequestApproval.builder()
                    .requestId(lastRequestId)
                    .userId(approverTemplate.getUserId())
                    .approvalStatus(AWAITING)
                    .build();
            requestApprovalRepo.insert(requestApproval);

            createRequestFormValue(requestDTO, lastRequestId);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateRequest(RequestDTO<RequestFormValue> requestDto, int id) {
        //update Request
        Request request = new Request();
        request.setResourceId(requestDto.getResourceId());
        request.setPurpose(requestDto.getPurpose());
        request.setNote(requestDto.getNote());
        request.setUpdatedAt(LocalDateTime.now());
        requestRepo.update(request, id);


//        //update request Approval
//        RequestApproval requestApproval = new RequestApproval();
//        int templateId = approverTemplateRepo.findById(requestDto.getResourceId()).getUserId();
//        int lastRequestId = requestRepo.lastRequestId();
//        requestApprovalRepo.delete(id);
//        requestApproval.setUserId(templateId);
//        requestApproval.setRequestId(lastRequestId);
//        requestApproval.setApprovalStatus(requestDto.getStatus());
//        requestApprovalRepo.insert(requestApproval);

        //update template approval template
        List<RequestFormValue> requestFormValueList = requestFormValueRepo.findByRequestId(id);
        RequestFormValue requestFormValue = requestFormValueList.get(0);
        if (requestFormValue.getTemplateId() != requestDto.getResourceId()) {
            requestFormValueRepo.delete(id);
            createRequestFormValue(requestDto, id);
            //update request Approval
            RequestApproval requestApproval = new RequestApproval();
            int templateId = approverTemplateRepo.findById(requestDto.getResourceId()).getUserId();
            int lastRequestId = requestRepo.lastRequestId();
            requestApprovalRepo.delete(id);
            requestApproval.setUserId(templateId);
            requestApproval.setRequestId(lastRequestId);
            requestApproval.setApprovalStatus(requestDto.getStatus());
            requestApprovalRepo.insert(requestApproval);
        } else {
            List<RequestFormValue> requestFormValues = requestDto.getRequestFormData();
            for (int index = 0; index < requestFormValues.size(); index++) {
                requestFormValueRepo.update(requestFormValues.get(index), id, requestFormValueList.get(index).getId());
            }
        }
    }

    private void createRequestFormValue(RequestDTO<RequestFormValue> requestDTO, int lastRequestId) {
        try {
            List<TemplateFromBuilder> templateFromBuilders = templateFormBuilderRepo.findById(requestDTO.getResourceId());
            List<RequestFormValue> requestFormValues = requestDTO.getRequestFormData();
            int count = 0;
            for (TemplateFromBuilder item : templateFromBuilders) {
                RequestFormValue requestFormValue = requestMapper.toTemplateFromBuilder(item);
                requestFormValue.setCreatedAt(LocalDateTime.now());
                requestFormValue.setUpdatedAt(LocalDateTime.now());
                requestFormValue.setValue(requestFormValues.get(count).getValue());
                requestFormValueRepo.insert(requestFormValue);
                count++;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<RequestDetailResponse> getAllRequest() {
        return requestRepo.findAll();
    }

    public List<RequestDetailResponse> getRequestOfUser(int userId) {
        List<RequestDetailResponse> requestDetailResponses = requestRepo.findByUserId(userId);
        if (requestDetailResponses.isEmpty()) {
            throw new RuntimeException("User has no requests");
        }
        return requestDetailResponses;
    }

    public List<RequestDetailResponse> getRequestNeedProcess(int userId) {
        List<RequestDetailResponse> requestDetailResponses = requestRepo.findByUserIdAndStatus(userId, AWAITING);
        if (requestDetailResponses.isEmpty()) {
            throw new RuntimeException("User has no requests to process");
        }
        return requestDetailResponses;
    }

    public RequestDetailResponse getRequestById(int id) {
        RequestDetailResponse requestDetailResponse = requestRepo.findById(id);
        if (requestDetailResponse == null) {
            throw new RuntimeException("Request not found");
        }
        return requestDetailResponse;
    }

    public String getNoteApprove(int id) {
        return requestApprovalRepo.findNoteApprove(id);
    }

    public RequestDTO<RequestFormValue> getRequestValue(int id) {
        RequestDTO<RequestFormValue> requestDto;
        requestDto = requestRepo.getRequestDtoById(id);
        List<RequestFormValue> requestFormValues = requestFormValueRepo.findByRequestId(id);
        requestDto.setRequestFormData(requestFormValues);
        return requestDto;
    }

    public void updateWhenApproval(RequestApproval requestApproval, int requestId) {
        Request request = Request.builder()
                .status(requestApproval.getApprovalStatus())
                .updatedAt(LocalDateTime.now())
                .build();
        requestRepo.updateStatus(request, requestId);

        requestApproval.setApprovalTime(LocalDateTime.now());
        requestApprovalRepo.update(requestApproval, requestId);
    }

    public void deleteRequest(int id) {
        RequestDetailResponse request = requestRepo.findById(id);
        if (request == null) {
            throw new RuntimeException("Request not found");
        }
        RequestApproval existingApproval = requestApprovalRepo.findById(id);
        if (existingApproval == null) {
            throw new RuntimeException("Request approval not found");
        }
        requestApprovalRepo.delete(id);
        requestFormValueRepo.delete(id);
        requestRepo.delete(id);
    }
}
