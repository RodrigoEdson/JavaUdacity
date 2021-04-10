package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface NoteMapper {

    @Insert("insert into notes (noteid, notetitle,notedescription, userid ) values (#{noteId}, #{noteTitle}, #{noteDescription}, #{user.userId}) ")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int insert (Note note);

}
