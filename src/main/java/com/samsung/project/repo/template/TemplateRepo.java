package com.samsung.project.repo.template;

import com.samsung.project.dto.template.TemplateDto;
import com.samsung.project.dto.template.TemplateDetail;
import com.samsung.project.model.template.Template;
import com.samsung.project.model.template.TemplateFromBuilder;
import org.apache.ibatis.annotations.*;

import java.util.List;

//private int id;
//private String description;
//private int tempFormId;
//private String name;
//private boolean status;
//private int createUserId;
//private LocalDateTime createAt;
//private LocalDateTime updateAt;
@Mapper
public interface TemplateRepo {
    @Insert("INSERT INTO template(description,name,status,create_user_id,created_at,updated_at) VALUES(#{description},#{name},#{status},#{createUserId},#{createdAt},#{updatedAt})")
    public int addTemplate(Template template);
    @Update("UPDATE template SET description = #{description}, name = #{name}, status = #{status},updated_at = #{updatedAt} WHERE id = #{id}")
    public int updateTemplate(Template template);
    @Select("SELECT * FROM template")
    public List<Template> getAllTemplate();
    @Delete("DELETE FROM template WHERE id = #{id}")
    public int deleteTemplate(int id);
    @Select("SELECT * FROM template WHERE id = #{id}")
    public Template getTemplateById(int id);
    @Select("SELECT last_value FROM template_id_seq")
    public int getTemplateLastValue();
    @Select("SELECT *,user_id as approver FROM \"template\"" +
            "INNER JOIN approval_template ON template.id = approval_template.template_id WHERE template_id = #{id}")
    public TemplateDto<TemplateFromBuilder> getTemplateDto(int id);
    @Select("SELECT template.id,template.name, template.description,users.username,template.updated_at,template.status FROM template \n" +
            "INNER JOIN approval_template ON template.id = approval_template.template_id\n" +
            "INNER JOIN users ON users.id = approval_template.user_id")
    public List<TemplateDetail> getTemplateList();
    @Select("SELECT template.id,template.name, template.description,users.username,template.updated_at,template.status FROM template \n" +
            "INNER JOIN approval_template ON template.id = approval_template.template_id\n" +
            "INNER JOIN users ON users.id = approval_template.user_id and template.status = true")
    public List<TemplateDetail> getTemplateListActive();
    @Select("SELECT template.id as id,template.name, template.description,users.username,template.updated_at,template.status,template.create_user_id FROM template \n" +
            "INNER JOIN approval_template ON template.id = approval_template.template_id\n" +
            "INNER JOIN users ON users.id = approval_template.user_id WHERE template_id = #{id}")
    public TemplateDetail getTemplateDetail(int id);

}
