package com.samsung.project.repo;

import com.samsung.project.model.Authority;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AuthorityRepo {
    @Insert("insert into authorities(username,authority) values(#{username},#{authority})")
    void save(Authority authority);

    @Select("SELECT * from authorities where username= #{username}")
    List<Authority> findByUsername(String username);
}
