package com.samsung.project.repo;

import com.samsung.project.model.TemplateFromBuilder;
import org.apache.ibatis.annotations.*;

import java.util.List;

//        id serial4 primary key NOT NULL,
//        "label" varchar,
//        place_holder varchar,
//        required varchar,
//        layout varchar,
//        "options" varchar,
//        template_id int,
//        create_user_id int not null,
//        created_at timestamp NOT NULL,
//        updated_at timestamp NOT null,
//        format_date_time timestamp,
//        default_value varchar,
//        type varchar
@Mapper
public interface TemplateFormBuilderRepo {
    @Insert("INSERT INTO template_form_builder(label,name,placeholder,required,layout,options,template_id,create_user_id,created_at,updated_at,format_date_time,value,type)"
            +"VALUES(#{label},#{name},#{placeholder},#{required},#{layout},#{options},#{templateId},#{createUserId},#{createdAt},#{updatedAt},#{formatDateTime},#{value},#{type})")
    public int insertTempFormBuilder(TemplateFromBuilder templateFromBuilder);
    @Update("UPDATE template_form_builder SET label = #{label},name = #{name},placeholder = #{placeholder},required = #{required},layout = #{layout},options = #{options},updated_at = #{updatedAt},format_date_time = #{formatDateTime}, value = #{value},type = #{type} WHERE template_id = #{templateId}")
    public int updateTempFormBuilder(TemplateFromBuilder templateFromBuilder);
    @Select("SELECT * FROM template_form_builder WHERE template_id = #{templateId}")
    public List<TemplateFromBuilder> getTemplateFromBuilderById(int id);
    @Delete("DELETE FROM template_form_builder WHERE template_id = #{templateId}")
    public int deleteTempFormBuilder(int templateId);
}
