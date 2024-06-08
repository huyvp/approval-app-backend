package com.samsung.project.service;

import com.samsung.project.dto.RequestDetail;
import com.samsung.project.dto.RequestDto;
import com.samsung.project.model.Request;
import com.samsung.project.model.RequestApproval;
import com.samsung.project.model.RequestFormValue;
import com.samsung.project.model.TemplateFromBuilder;
import com.samsung.project.repo.RequestApprovalRepo;
import com.samsung.project.repo.RequestFormValueRepo;
import com.samsung.project.repo.RequestRepo;
import com.samsung.project.repo.ApproverTemplateRepo;
import com.samsung.project.repo.TemplateFormBuilderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class RequestService {
    private final RequestRepo requestRepo;
    private final RequestApprovalRepo requestApprovalRepo;
    private final RequestFormValueRepo requestFormValueRepo;
    private final TemplateFormBuilderRepo templateFormBuilderRepo;
    private final ApproverTemplateRepo approverTemplateRepo;

    @Autowired
    public RequestService(RequestRepo requestRepo, RequestApprovalRepo requestApprovalRepo, RequestFormValueRepo requestFormValueRepo, TemplateFormBuilderRepo templateFormBuilderRepo, ApproverTemplateRepo approverTemplateRepo) {
        this.requestRepo = requestRepo;
        this.requestApprovalRepo = requestApprovalRepo;
        this.requestFormValueRepo = requestFormValueRepo;
        this.templateFormBuilderRepo = templateFormBuilderRepo;
        this.approverTemplateRepo = approverTemplateRepo;
    }

    public int createRequest(RequestDto<RequestFormValue> requestDto) {
        Request request = new Request();
        request.setResourceId(requestDto.getResourceId());
        request.setPurpose(requestDto.getPurpose());
        request.setNote(requestDto.getNote());
        request.setCreatedAt(LocalDateTime.now());
        request.setUpdatedAt(LocalDateTime.now());
        request.setCreateUserId(requestDto.getCreateUserId());
        request.setStatus(requestDto.getStatus());
        requestRepo.insertRequest(request);

        RequestApproval requestApproval = new RequestApproval();
        int id = approverTemplateRepo.getApproverById(requestDto.getResourceId()).getUserId();
        int lastRequestId = requestRepo.lastRequestId();
        requestApproval.setUserId(id);
        requestApproval.setRequestId(lastRequestId);
        requestApproval.setApprovalStatus("awaiting");
        requestApprovalRepo.addRequestApproval(requestApproval);

        addRequestFormValue(requestDto, lastRequestId);
        return 1;
    }

    public int updateRequest(RequestDto<RequestFormValue> requestDto, int id) {
        //update Request
        Request request = new Request();
        request.setResourceId(requestDto.getResourceId());
        request.setPurpose(requestDto.getPurpose());
        request.setNote(requestDto.getNote());
        request.setUpdatedAt(LocalDateTime.now());
        requestRepo.updateRequest(request, id);


//        //update request Approval
//        RequestApproval requestApproval = new RequestApproval();
//        int templateId = approverTemplateRepo.getApproverById(requestDto.getResourceId()).getUserId();
//        int lastRequestId = requestRepo.lastRequestId();
//        requestApprovalRepo.deleteRequestApproval(id);
//        requestApproval.setUserId(templateId);
//        requestApproval.setRequestId(lastRequestId);
//        requestApproval.setApprovalStatus(requestDto.getStatus());
//        requestApprovalRepo.addRequestApproval(requestApproval);

        //update template approval template
        RequestFormValue[] requestFormValueList = requestFormValueRepo.getRequestFormValueByRequestId(id);
        RequestFormValue requestFormValue = requestFormValueList[0];
        if (requestFormValue.getTemplateId() != requestDto.getResourceId()) {
            requestFormValueRepo.deleteRequestFormValue(id);
            addRequestFormValue(requestDto, id);
            //update request Approval
            RequestApproval requestApproval = new RequestApproval();
            int templateId = approverTemplateRepo.getApproverById(requestDto.getResourceId()).getUserId();
            int lastRequestId = requestRepo.lastRequestId();
            requestApprovalRepo.deleteRequestApproval(id);
            requestApproval.setUserId(templateId);
            requestApproval.setRequestId(lastRequestId);
            requestApproval.setApprovalStatus(requestDto.getStatus());
            requestApprovalRepo.addRequestApproval(requestApproval);
        } else {
            RequestFormValue[] requestFormValues = requestDto.getRequestFormValueData();
            for (int index = 0; index < requestFormValues.length; index++) {
                requestFormValueRepo.updateRequestFormValue(requestFormValues[index], id, requestFormValueList[index].getId());
            }
        }
        return 1;
    }

    private void addRequestFormValue(RequestDto<RequestFormValue> requestDto, int lastRequestId) {
        List<TemplateFromBuilder> templateFromBuilders = templateFormBuilderRepo.getTemplateFromBuilderById(requestDto.getResourceId());
        RequestFormValue[] requestFormValues = requestDto.getRequestFormValueData();

        RequestFormValue requestFormValue = new RequestFormValue();
        for (int index = 0; index < templateFromBuilders.size(); index++) {
            requestFormValue.setLabel(templateFromBuilders.get(index).getLabel());
            requestFormValue.setPlaceholder(templateFromBuilders.get(index).getPlaceholder());
            requestFormValue.setRequired(templateFromBuilders.get(index).isRequired());
            requestFormValue.setLayout(templateFromBuilders.get(index).getLayout());
            requestFormValue.setOptions(templateFromBuilders.get(index).getOptions());
            requestFormValue.setRequestId(lastRequestId);
            requestFormValue.setTemplateId(requestDto.getResourceId());
            requestFormValue.setCreateUserId(templateFromBuilders.get(index).getCreateUserId());
            requestFormValue.setCreatedAt(LocalDateTime.now());
            requestFormValue.setUpdatedAt(LocalDateTime.now());
            requestFormValue.setFormatDateTime(templateFromBuilders.get(index).getFormatDateTime());
            requestFormValue.setValue(requestFormValues[index].getValue());
            requestFormValue.setType(templateFromBuilders.get(index).getType());
            requestFormValueRepo.addRequestFormValue(requestFormValue);

        }
    }

    public List<RequestDetail> getAllRequest() {
        return requestRepo.getAllRequest();
    }
    public List<RequestDetail> getMyRequestDetail(int id) {
        return requestRepo.getMyRequestDetail(id);
    }
    public List<RequestDetail> getRequestNeedProcess(int id){
        return requestRepo.getRequestNeedProcess(id);
    }
    public RequestDetail getRequestById(int id) {
        return requestRepo.getRequestById(id);
    }
    public String getNoteApprove(int id){
        return requestApprovalRepo.getNoteApprove(id);
    }

    public RequestDto<RequestFormValue> getRequestDto(int id) {
        RequestDto<RequestFormValue> requestDto;
        requestDto = requestRepo.getRequestDtoById(id);
        RequestFormValue[] requestFormValues = requestFormValueRepo.getRequestFormValueByRequestId(id);
        requestDto.setRequestFormValueData(requestFormValues);
        return requestDto;
    }

    public int updateWhenApproval(RequestApproval requestApproval, int requestId) {
        Request request = new Request();
        request.setStatus(requestApproval.getApprovalStatus());
        requestRepo.updateRequestStatus(request, requestId);
        requestApproval.setApprovalTime(LocalDateTime.now());
        requestApprovalRepo.updateRequestApproval(requestApproval, requestId);
        return 1;
    }

    public int deleteRequest(int id) {
        requestApprovalRepo.deleteRequestApproval(id);
        requestFormValueRepo.deleteRequestFormValue(id);
        requestRepo.deleteRequest(id);
        return 1;
    }
}
