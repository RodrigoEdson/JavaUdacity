package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.FileDTO;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
@RequestMapping("/file")
public class FileController {

    private FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping
    public String fileUpload(@RequestParam("fileUpload")MultipartFile fileUpload, Authentication auth, RedirectAttributes redirectAttributes) throws IOException {
        User user = (User) auth.getDetails();

        InputStream fis = fileUpload.getInputStream();

        File file = new File();
        file.setUserId(user.getUserId());
        file.setFileName(fileUpload.getOriginalFilename());
        file.setFileSize((double) fileUpload.getSize());
        file.setContentType(fileUpload.getContentType());
        file.setFileData(fis.readAllBytes());

        fileService.saveFile(file);

        redirectAttributes.addFlashAttribute("activeTab", "files");

        return "redirect:/home";
    }
//https://knowledge.udacity.com/questions/497201
    @PostMapping("/delete")
    public String deleteFile ( RedirectAttributes redirectAttributes,@ModelAttribute FileDTO fileDTO){

        fileService.deleteFile(fileDTO.getFileId());

        redirectAttributes.addFlashAttribute("activeTab", "files");

        return "redirect:/home";
    }

}
