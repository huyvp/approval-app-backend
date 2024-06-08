package com.samsung.project.repo;

import com.samsung.project.model.RequestApproval;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RequestApprovalRepo {
//    private int userId;
//    private int requestId;
//    private boolean approvalStatus;
//    private LocalDateTime approvalTime;
//    private String comment;
    @Insert("INSERT INTO request_approval(user_id,request_id,approval_status) VALUES(#{userId},#{requestId},#{approvalStatus})")
    public int addRequestApproval(RequestApproval requestApproval);

    @Update("UPDATE request_approval SET approval_status=#{requestApproval.approvalStatus},approval_time=#{requestApproval.approvalTime}, comment=#{requestApproval.comment} " +
            "WHERE request_id=#{requestId}")
    public int updateRequestApproval(RequestApproval requestApproval,int requestId);

    @Delete("DELETE FROM request_approval WHERE request_id=#{id}")
    public int deleteRequestApproval(int id);

    @Select("SELECT user_id From request_approval Where request_id=#{id}")
    public List<Integer> getUserApprovalById(int id);
    @Select("SELECT comment From request_approval Where request_id=#{id}")
    public String getNoteApprove(int id);
}
