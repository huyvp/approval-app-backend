package com.samsung.project.repo;

import com.samsung.project.model.Authority;
import com.samsung.project.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserRepo {
    @Insert("INSERT INTO users(username,password,email,created_at,updated_at) VALUES (#{username},#{password},#{email},#{createdAt},#{updatedAt})")
    void save(User user);

    @Select("SELECT * FROM users WHERE username= #{username}")
    User findByName(String username);

    @Select("SELECT * FROM users WHERE id= #{id}")
    User findById(int id);

    @Select("SELECT * FROM users LIMIT #{limit} OFFSET #{offset}")
    List<User> findAll(int offset, int limit);

    @Select("select u.id,u.username,a.authority from users u inner join authorities a on a.username = u.username where u.id = #{id}")
    Authority findUserAndAuthority(int id);
}
