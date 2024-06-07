package com.samsung.project.repo;

import com.samsung.project.model.Authority;
import com.samsung.project.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserRepo {
    @Select("SELECT * from users where username= #{username}")
    User findUserByUsername(String username);
    @Select("SELECT * from users where id= #{id}")
    User findUserById(int id);
    @Select("SELECT * from users")
    List<User> findAllUser();
    @Select("select u.id,u.username,a.authority from users u inner join authorities a on a.username = u.username where u.id = #{id}")
    Authority findUserAndAuthority(int id);
    @Insert("insert into users(username,password,email,created_at,updated_at) values(#{username},#{password},#{email},#{createdAt},#{updatedAt})")
    int save(User user);
}
