package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public int createNote(Note note){
        return noteMapper.insert(note);
    }

    public int udateNote(Note note){
        return noteMapper.update(note);
    }

    public List<Note> getNotesByUserID (Integer userId){
        return noteMapper.getNotesByUserId(userId);
    }

    public void deleteNote(Integer noteId) {
        noteMapper.delete(noteId);
    }
}
