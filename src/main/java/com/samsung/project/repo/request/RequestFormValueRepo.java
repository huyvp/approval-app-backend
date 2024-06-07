package com.samsung.project.repo.request;

import com.samsung.project.model.request.RequestFormValue;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RequestFormValueRepo {
    @Insert("INSERT INTO request_form_value(label,placeholder,required,layout,options,request_id,template_id,create_user_id,created_at,updated_at,format_date_time,value,type)" +
            "VALUES(#{label},#{placeholder},#{required},#{layout},#{options},#{requestId},#{templateId},#{createUserId},#{createdAt},#{updatedAt},#{formatDateTime},#{value},#{type})")
    public int addRequestFormValue(RequestFormValue requestFormValue);
    @Update("UPDATE request_form_value SET value=#{requestFormValue.value} WHERE request_id=#{requestId} AND id=#{id}")
    public int updateRequestFormValue(RequestFormValue requestFormValue,int requestId, int id);
    @Delete("DELETE From request_form_value WHERE request_id=#{requestId}")
    public int deleteRequestFormValue(int id);

    @Select("SELECT * From request_form_value")
    public List<RequestFormValue> getAllRequestFormValue();

    @Select("SELECT * From request_form_value WHERE request_id=#{id}")
    public RequestFormValue[] getRequestFormValueByRequestId(int id);

    @Select("SELECT * From request_form_value WHERE template_id=#{templateId}")
    public List<RequestFormValue> getRequestFormValueTemplateId(int id);
}
