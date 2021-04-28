package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteDTO;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/note")
public class NoteController {

    private NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping
    public String insertUpdateNote(Authentication auth, @ModelAttribute Note note, RedirectAttributes redirectAttributes){
        User user = (User) auth.getDetails();
        note.setUserId(user.getUserId());

        if (note.getNoteId() == null) {
            noteService.createNote(note);
        }else{
            noteService.udateNote(note);
        }

        redirectAttributes.addFlashAttribute("noteMsgOK","Note "+note.getNoteTitle()+" successfully saved");
        redirectAttributes.addFlashAttribute("activeTab", "notes");
        return "redirect:/home";
    }

    @PostMapping("/delete")
    public String deleteNote (Authentication auth, RedirectAttributes redirectAttributes, @ModelAttribute NoteDTO noteDTO){

        noteService.deleteNote(noteDTO.getNoteId());

        redirectAttributes.addFlashAttribute("noteMsgOK","Note successfully deleted");
        redirectAttributes.addFlashAttribute("activeTab", "notes");

        return "redirect:/home";
    }

}
