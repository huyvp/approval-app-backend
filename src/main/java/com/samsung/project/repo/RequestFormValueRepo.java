package com.samsung.project.repo;

import com.samsung.project.model.RequestFormValue;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RequestFormValueRepo {
    @Insert("INSERT INTO request_form_value(label,placeholder,required,layout,options,request_id,template_id,create_user_id,created_at,updated_at,format_date_time,value,type)" + "VALUES(#{label},#{placeholder},#{required},#{layout},#{options},#{requestId},#{templateId},#{createUserId},#{createdAt},#{updatedAt},#{formatDateTime},#{value},#{type})")
    int insert(RequestFormValue requestFormValue);

    @Update("UPDATE request_form_value SET value=#{requestFormValue.value} WHERE request_id=#{requestId} AND id=#{id}")
    int update(RequestFormValue requestFormValue, int requestId, int id);

    @Delete("DELETE From request_form_value WHERE request_id=#{requestId}")
    int delete(int id);

    @Select("SELECT * From request_form_value")
    List<RequestFormValue> findAll();

    @Select("SELECT * From request_form_value WHERE request_id=#{id}")
    List<RequestFormValue> findByRequestId(int id);

    @Select("SELECT * From request_form_value WHERE template_id=#{templateId}")
    List<RequestFormValue> findByTemplateId(int id);
}
