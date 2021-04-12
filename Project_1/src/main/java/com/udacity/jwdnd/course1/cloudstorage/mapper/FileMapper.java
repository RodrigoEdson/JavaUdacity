package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {

    @Insert("insert into files (fileid, filename, contenttype, filesize, userid, filedata ) values (#{fileId}, #{fileName}, #{contentType}, #{fileSize}, #{userId}, #{fileData}) ")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insert (File file);

    @Select("select * from files where userId = #{userId}")
    List<File> getFilesListByUserId(Integer userId);

    @Delete("delete from files where fileId = #{fileId}")
    void delete(int fileId);
}

