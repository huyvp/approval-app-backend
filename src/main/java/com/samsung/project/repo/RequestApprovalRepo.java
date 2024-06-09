package com.samsung.project.repo;

import com.samsung.project.model.RequestApproval;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RequestApprovalRepo {
    @Insert("INSERT INTO request_approval(user_id,request_id,approval_status) VALUES(#{userId},#{requestId},#{approvalStatus})")
    public int insert(RequestApproval requestApproval);

    @Update("UPDATE request_approval SET approval_status=#{requestApproval.approvalStatus},approval_time=#{requestApproval.approvalTime}, comment=#{requestApproval.comment} " +
            "WHERE request_id=#{requestId}")
    public int update(RequestApproval requestApproval, int requestId);

    @Update("UPDATE request_approval SET approval_status=#{status} " +
            "WHERE request_id=#{requestId}")
    public int updateStatus(String status, int requestId);

    @Delete("DELETE FROM request_approval WHERE request_id=#{id}")
    public int delete(int id);

    @Select("SELECT * FROM request_approval WHERE request_id=#{id}")
    public RequestApproval findByRequestId(int id);

    @Select("SELECT comment FROM request_approval WHERE request_id=#{id}")
    public String findNoteApprove(int id);
}
