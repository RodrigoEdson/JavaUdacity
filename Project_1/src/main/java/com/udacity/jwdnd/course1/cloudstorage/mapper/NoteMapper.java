package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {

    @Insert("insert into notes (noteid, notetitle,notedescription, userid ) values (#{noteId}, #{noteTitle}, #{noteDescription}, #{userId}) ")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int insert (Note note);

    @Update("update notes set notetitle = #{noteTitle}, notedescription = #{noteDescription} where noteid = #{noteId}")
    int update (Note note);

    @Select("select * from notes where userid = #{noteId}")
    List<Note> getNotesByUserId(Integer noteId);

    @Delete("Delete from notes where noteid = #{noteId}")
    void delete(Integer noteId);
}
