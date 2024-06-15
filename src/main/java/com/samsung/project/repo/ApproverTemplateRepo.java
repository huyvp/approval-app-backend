package com.samsung.project.repo;

import com.samsung.project.model.ApproverTemplate;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ApproverTemplateRepo {
    @Insert("INSERT INTO approval_template(user_id,template_id) VALUES(#{userId},#{templateId})")
    int insert(ApproverTemplate approverTemplate);

    @Update("UPDATE approval_template SET user_id = #{userId} WHERE template_id = #{templateId}")
    int update(ApproverTemplate approverTemplate);

    @Delete("DELETE FROM approval_template WHERE template_id = #{id}")
    int delete(int id);

    @Select("SELECT * FROM approval_template WHERE template_id = #{templateId}")
    ApproverTemplate findById(int templateId);
}
