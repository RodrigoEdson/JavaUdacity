package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    private CredentialService credentialService;
    private NoteService noteService;

    public HomeController(CredentialService credentialService, NoteService noteService) {
        this.credentialService = credentialService;
        this.noteService = noteService;
    }

    @GetMapping()
    public String getHomePage(Authentication auth, Model model) {
        User user = (User) auth.getDetails();

        List<Credential> credentialsList = this.credentialService.getCredentialsByUserID(user.getUserId());
        List<Note> notesList = this.noteService.getNotesByUserID(user.getUserId());

        model.addAttribute("credentialsList", credentialsList);
        model.addAttribute("notesList", notesList);

        return "home";
    }

}