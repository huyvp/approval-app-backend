package com.samsung.project.repo;

import com.samsung.project.model.TemplateFromBuilder;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TemplateFormBuilderRepo {
    @Insert("INSERT INTO template_form_builder(label,name,placeholder,required,layout,options,template_id,create_user_id,created_at,updated_at,format_date_time,value,type)" +
            "VALUES(#{label},#{name},#{placeholder},#{required},#{layout},#{options},#{templateId},#{createUserId},#{createdAt},#{updatedAt},#{formatDateTime},#{value},#{type})")
    int insert(TemplateFromBuilder templateFromBuilder);

    @Update("UPDATE template_form_builder SET label = #{label},name = #{name},placeholder = #{placeholder},required = #{required},layout = #{layout},options = #{options},updated_at = #{updatedAt},format_date_time = #{formatDateTime}, value = #{value},type = #{type} WHERE template_id = #{templateId}")
    int update(TemplateFromBuilder templateFromBuilder);

    @Delete("DELETE FROM template_form_builder WHERE template_id = #{templateId}")
    int delete(int templateId);

    @Select("SELECT * FROM template_form_builder WHERE template_id = #{templateId}")
    List<TemplateFromBuilder> findById(int id);

}
