package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select * from Users where username = #{username}")
    User getUser(String username);

    @Insert("insert into users (userid, username, salt, password, firstname, lastname) values (#{userId}, #{username}, #{salt}, #{password}, #{firstName}, #{lastName}) ")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    int insert(User user);
}


