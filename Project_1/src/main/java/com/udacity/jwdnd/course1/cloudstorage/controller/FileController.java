package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String fileUpload(@RequestParam("fileUpload")MultipartFile fileUpload, Model model, Authentication auth, RedirectAttributes redirectAttributes) throws IOException {
        User user = (User) auth.getDetails();

        InputStream fis = fileUpload.getInputStream();

        File file = new File();
        file.setUserId(user.getUserId());
        file.setFileName(fileUpload.getOriginalFilename());
        file.setFileSize((double) fileUpload.getSize());
        file.setContentType(fileUpload.getContentType());
        file.setFileData(fis.readAllBytes());

        fileService.saveFile(file);

        List<File> filesList = fileService.getFilesListByUserId(user.getUserId());
        redirectAttributes.addFlashAttribute("filesList", filesList);
        redirectAttributes.addFlashAttribute("activeTab", "files");

        return "redirect:/home";
    }

    public String deleteFile (Authentication auth, RedirectAttributes redirectAttributes, @PathVariable int fileId){
        User user = (User) auth.getDetails();

        fileService.deleteFile(fileId);

        List<File> filesList = fileService.getFilesListByUserId(user.getUserId());
        redirectAttributes.addFlashAttribute("filesList", filesList);
        redirectAttributes.addFlashAttribute("activeTab", "files");

        return "redirect:/home";
    }

}
