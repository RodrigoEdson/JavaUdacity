package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.FileDTO;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
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
    public String fileUpload(@RequestParam("fileUpload")MultipartFile fileUpload,  Authentication auth, RedirectAttributes redirectAttributes) throws IOException {
        User user = (User) auth.getDetails();

        InputStream fis = fileUpload.getInputStream();

        File file = new File();
        file.setUserId(user.getUserId());
        file.setFileName(fileUpload.getOriginalFilename());
        file.setFileSize((double) fileUpload.getSize());
        file.setContentType(fileUpload.getContentType());
        file.setFileData(fis.readAllBytes());

        if (file.getFileName() == null || file.getFileName().isBlank()){
            redirectAttributes.addFlashAttribute("fileMsgError","No file selected");
        }else if (fileService.isFileAlreadyLoaded(user.getUserId(), file.getFileName()) ){
            redirectAttributes.addFlashAttribute("fileMsgError","File already load, choose another one");
        }else {
            fileService.saveFile(file);
            redirectAttributes.addFlashAttribute("fileMsgOK","File successfully saved");
        }
        redirectAttributes.addFlashAttribute("activeTab", "files");
        return "redirect:/home";
    }

    @PostMapping("/delete")
    public String deleteFile ( RedirectAttributes redirectAttributes, Authentication auth, @ModelAttribute FileDTO fileDTO){

        User user = (User) auth.getDetails();
        File file = fileService.getFileById(user.getUserId(), fileDTO.getFileId());
        fileService.deleteFile(fileDTO.getFileId());

        redirectAttributes.addFlashAttribute("fileMsgOK","File "+file.getFileName()+" successfully deleted");
        redirectAttributes.addFlashAttribute("activeTab", "files");

        return "redirect:/home";
    }

    @GetMapping("/view/{fileId}")
    public ResponseEntity viewFile(Authentication auth, @PathVariable("fileId") Integer fileId){
        User user = (User) auth.getDetails();

        File file = fileService.getFileById(user.getUserId(), fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=\""+file.getFileName()+"\"")
                .body(file.getFileData());
    }

}
