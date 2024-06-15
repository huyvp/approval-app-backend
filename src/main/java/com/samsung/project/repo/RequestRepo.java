package com.samsung.project.repo;

import com.samsung.project.dto.RequestDTO;
import com.samsung.project.model.Request;
import com.samsung.project.model.RequestFormValue;
import com.samsung.project.response.RequestDetailResponse;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RequestRepo {
    @Insert("INSERT INTO request(resource_id,purpose,note,created_at,updated_at,create_user_id,status) " +
            "VALUES(#{resourceId},#{purpose},#{note},#{createdAt},#{updatedAt},#{createUserId},#{status})")
    int insert(Request request);

    @Update("UPDATE request SET resource_id=#{request.resourceId},purpose = #{request.purpose},note = #{request.note},updated_at = #{request.updatedAt} " +
            "WHERE id = #{id}")
    int update(Request request, int id);

    @Update("UPDATE request SET status=#{request.status}, updated_at=#{request.updatedAt} WHERE id = #{id}")
    int updateStatus(Request request, int id);

    @Delete("DELETE FROM request WHERE id = #{id}")
    int delete(int id);

    @Select("SELECT a.purpose as requests,a.note as description,c.username as approver,a.updated_at as updatedAt,b.approval_status as status " +
            "FROM request a INNER JOIN request_approval b on b.request_id= a.id INNER JOIN users c on c.id=b.user_id " +
            "WHERE a.id=#{id}")
    RequestDetailResponse findById(int id);

    @Select("SELECT a.id,a.purpose as requests,a.note as description,c.username as approver,a.updated_at as updatedAt,a.status as status " +
            "FROM request a INNER JOIN request_approval b on b.request_id= a.id " +
            "INNER JOIN users c on c.id=b.user_id")
    List<RequestDetailResponse> findAll();

    @Select("SELECT a.id,a.purpose as requests,a.note as description,c.username as approver,a.updated_at as updatedAt,a.status as status " +
            "From request a INNER JOIN request_approval b on b.request_id= a.id " +
            "INNER JOIN users c on c.id=b.user_id " +
            "WHERE a.create_user_id=#{id}")
//            "limit #{pageSize} offset #{currentPage}")
    List<RequestDetailResponse> findByUserId(int id);

    @Select("SELECT a.id,a.purpose as requests,a.note as description,c.username as approver,a.updated_at as updatedAt,b.approval_status as status " +
            "From request a INNER JOIN request_approval b on b.request_id= a.id " +
            "INNER JOIN users c on c.id=b.user_id " +
            "WHERE b.user_id=#{id} and b.approval_status=#{status}")
//            "limit #{pageSize} offset #{currentPage}")
    List<RequestDetailResponse> findByUserIdAndStatus(int id, String status);

    @Select("SELECT * From request where id=#{id}")
    RequestDTO<RequestFormValue> getRequestDtoById(int id);

    @Select("SELECT last_value from request_id_seq")
    int lastRequestId();

}
