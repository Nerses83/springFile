package com.files.files.controller;

import com.files.files.model.File;
import com.files.files.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Nerses
 * Date: 2/17/18.
 * Time: 11:20 AM.
 * To change this template use File | Settings | File Templates.
 */

@Controller
public class FileController {

    @Autowired
    FileService fileService;

    @RequestMapping("/")
    public ModelAndView getAllFiles(){
        List<File> files = fileService.getAll();
        ModelAndView modelAndView = new ModelAndView("file");
        modelAndView.addObject("files",files);
        return modelAndView;
    }
}
