package com.files.files.service.impl;

import com.files.files.dao.FileDao;
import com.files.files.model.File;
import com.files.files.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Nerses
 * Date: 2/17/18.
 * Time: 11:06 AM.
 * To change this template use File | Settings | File Templates.
 */
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    FileDao fileDao;

    @Override
    public List<File> getAll() {
        return fileDao.findAll();
    }

    @Override
    public File save(File file) {
        return fileDao.saveAndFlush(file);
    }

    @Override
    public void deleteById(long id) {
        fileDao.delete(id);
    }

    @Override
    public File getById(long id) {
        return  fileDao.getOne(id);
    }
}
