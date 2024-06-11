package com.samsung.project.repo;

import com.samsung.project.dto.TemplateDTO;
import com.samsung.project.model.Template;
import com.samsung.project.response.TemplateDetailResponse;
import com.samsung.project.response.TemplateValueResponse;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TemplateRepo {
    @Insert("INSERT INTO template(description,name,status,create_user_id,created_at,updated_at) VALUES(#{description},#{name},#{status},#{createUserId},#{createdAt},#{updatedAt})")
    public int insert(Template template);

    @Update("UPDATE template SET description = #{description}, name = #{name}, status = #{status},updated_at = #{updatedAt} WHERE id = #{id}")
    public int update(Template template);

    @Select("SELECT * FROM template")
    public List<Template> findAll();

    @Delete("DELETE FROM template WHERE id = #{id}")
    public int delete(int id);

    @Select("SELECT * FROM template WHERE id = #{id}")
    public Template findById(int id);

    @Select("SELECT * FROM template WHERE name = #{name}")
    public Template findByName(String name);

    @Select("SELECT last_value FROM template_id_seq")
    public int findLastValue();

    @Select("SELECT *,user_id as approver FROM \"template\"" +
            "INNER JOIN approval_template ON template.id = approval_template.template_id WHERE template_id = #{id}")
    public TemplateValueResponse findValue(int id);

    @Select("SELECT template.id,template.name, template.description,users.username,template.updated_at,template.status FROM template \n" +
            "INNER JOIN approval_template ON template.id = approval_template.template_id\n" +
            "INNER JOIN users ON users.id = approval_template.user_id")
    public List<TemplateDetailResponse> findListDetail();

    @Select("SELECT template.id,template.name, template.description,users.username,template.updated_at,template.status FROM template \n" +
            "INNER JOIN approval_template ON template.id = approval_template.template_id\n" +
            "INNER JOIN users ON users.id = approval_template.user_id and template.status = true")
    public List<TemplateDetailResponse> findListDetailActive();

    @Select("SELECT template.id as id,template.name, template.description,users.username,template.updated_at,template.status,template.create_user_id FROM template \n" +
            "INNER JOIN approval_template ON template.id = approval_template.template_id\n" +
            "INNER JOIN users ON users.id = approval_template.user_id WHERE template_id = #{id}")
    public TemplateDetailResponse findDetail(int id);

}
