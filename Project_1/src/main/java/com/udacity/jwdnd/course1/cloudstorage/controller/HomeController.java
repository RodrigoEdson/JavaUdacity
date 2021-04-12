package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
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
    private FileService fileService;

    public HomeController(CredentialService credentialService, NoteService noteService, FileService fileService) {
        this.credentialService = credentialService;
        this.noteService = noteService;
        this.fileService = fileService;
    }

    @GetMapping()
    public String getHomePage(Authentication auth, Model model) {
        User user = (User) auth.getDetails();

        List<Credential> credentialsList = this.credentialService.getCredentialsByUserID(user.getUserId());
        List<Note> notesList = this.noteService.getNotesByUserID(user.getUserId());
        List<File> filesList = this.fileService.getFilesListByUserId(user.getUserId());

        model.addAttribute("filesList", filesList);
        model.addAttribute("credentialsList", credentialsList);
        model.addAttribute("notesList", notesList);

        return "home";
    }

}