package com.samsung.project.repo;

import com.samsung.project.model.RequestApproval;
import org.apache.ibatis.annotations.*;

@Mapper
public interface RequestApprovalRepo {
    @Insert("INSERT INTO request_approval(user_id,request_id,approval_status) VALUES(#{userId},#{requestId},#{approvalStatus})")
    int insert(RequestApproval requestApproval);

    @Update("UPDATE request_approval SET approval_status=#{requestApproval.approvalStatus},approval_time=#{requestApproval.approvalTime}, comment=#{requestApproval.comment} " +
            "WHERE request_id=#{requestId}")
    int update(RequestApproval requestApproval, int requestId);

    @Update("UPDATE request_approval SET approval_status=#{status} " +
            "WHERE request_id=#{requestId}")
    int updateStatus(String status, int requestId);

    @Delete("DELETE FROM request_approval WHERE request_id=#{id}")
    int delete(int id);

    @Select("SELECT * FROM request_approval WHERE request_id=#{id}")
    RequestApproval findById(int id);

    @Select("SELECT comment FROM request_approval WHERE request_id=#{id}")
    String findNoteApprove(int id);
}
