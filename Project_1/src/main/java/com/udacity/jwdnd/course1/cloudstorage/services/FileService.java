package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {

    FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public int saveFile (File file){
        return fileMapper.insert(file);
    }

    public List<File> getFilesListByUserId(Integer userId) {
        return fileMapper.getFilesListByUserId(userId);
    }

    public void deleteFile (int fileId){
        fileMapper.delete(fileId);
    }

    public File getFileById(Integer userId, Integer fileId) {
        return fileMapper.getFileById(userId,fileId);
    }
}
