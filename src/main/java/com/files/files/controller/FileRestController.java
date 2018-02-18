package com.files.files.controller;

import com.files.files.model.AjaxResponse;
import com.files.files.model.File;
import com.files.files.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created with IntelliJ IDEA.
 * User: Nerses
 * Date: 2/17/18.
 * Time: 12:55 PM.
 * To change this template use File | Settings | File Templates.
 */
@RestController
public class FileRestController {

    private final static String UPLOADED_FOLDER = "/home/nerses/tmp/";

    @Autowired
    FileService fileService;

    @RequestMapping(value = "/file", method = RequestMethod.POST)
    public ResponseEntity<AjaxResponse<File>> upload(@RequestParam("file") MultipartFile uploadedFile){
        if (uploadedFile == null || uploadedFile.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            byte[] bytes = uploadedFile.getBytes();
            String fileName = uploadedFile.getOriginalFilename();
            String fileStoredName = System.currentTimeMillis() + uploadedFile.getOriginalFilename();
            Path dirPath = Paths.get(UPLOADED_FOLDER);
            Path path = Paths.get(UPLOADED_FOLDER + fileStoredName);
            if(!Files.exists(dirPath)){
                Files.createDirectory(dirPath);
            }
            Files.write(path, bytes);
            File file = new File();
            file.setFileName(fileName);
            file.setFilePath(fileStoredName);
            file = fileService.save(file);
            AjaxResponse<File> ajaxResponse = new AjaxResponse<>("success",file);
            return ResponseEntity.ok(ajaxResponse);

        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/file/{id}", method = RequestMethod.PUT)
    public ResponseEntity<AjaxResponse<File>> update(@PathVariable("id") long id,@RequestParam("content") String content){

        try {
            byte[] bytes = content.getBytes();
            File file = fileService.getById(id);
            Path path = Paths.get(UPLOADED_FOLDER + file.getFilePath());
            Files.write(path, bytes);
            AjaxResponse<File> ajaxResponse = new AjaxResponse<>("success",null);
            return ResponseEntity.ok(ajaxResponse);

        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping(value = "/file/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<AjaxResponse<File>> deleteFile(@PathVariable("id") long id) {
        fileService.deleteById(id);
        AjaxResponse<File> ajaxResponse = new AjaxResponse<>("success",null);
        return ResponseEntity.ok(ajaxResponse);
    }

    @RequestMapping(value = "/file/{id}", method = RequestMethod.GET)
    public ResponseEntity<AjaxResponse<String>> getFile(@PathVariable("id") long id) {
        File file = fileService.getById(id);
        Path path = Paths.get(UPLOADED_FOLDER + file.getFilePath());
        byte[] bytes;
        try {
            bytes = Files.readAllBytes(path);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        String fileContent = new String(bytes);
        AjaxResponse<String> ajaxResponse = new AjaxResponse<>("success",fileContent);
        return ResponseEntity.ok(ajaxResponse);
    }

}
