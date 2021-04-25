package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {

    @Insert("insert into credentials (credentialid, url, username, key, password, userid ) values (#{credentialId}, #{url}, #{userName}, #{key}, #{password}, #{userId}) ")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int insert (Credential credential);

    @Update("update credentials set url = #{url}, username = #{userName} , key = #{key}, password = #{password} where credentialid = #{credentialId}")
    int update (Credential credential);

    @Select("select * from credentials where userid = #{userId}")
    List<Credential> getCredentialsByUserId(Integer userId);

    @Delete("Delete from credentials where credentialid = ${credentialId}")
    void delete(Integer credentialId);

    @Select("select * from credentials where userid = #{userId} and  credentialid = ${credentialId}")
    Credential getCredentialByCredentialId(Integer userId, int credentialId);
}


