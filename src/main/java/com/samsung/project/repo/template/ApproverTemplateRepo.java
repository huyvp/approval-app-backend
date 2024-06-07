package com.samsung.project.repo.template;

import com.samsung.project.model.template.ApproverTemplate;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ApproverTemplateRepo {
    @Insert("INSERT INTO approval_template(user_id,template_id) VALUES(#{userId},#{templateId})")
    public int insertApprover(ApproverTemplate approverTemplate);
    @Update("UPDATE approval_template SET user_id = #{userId} WHERE template_id = #{templateId}")
    public int updateApprover(ApproverTemplate approverTemplate);
    @Delete("DELETE FROM approval_template WHERE template_id = #{id}")
    public int deleteApprover(int id);
    @Select("SELECT * FROM approval_template WHERE template_id = #{templateId}")
    public ApproverTemplate getApproverById(int templateId);
}
