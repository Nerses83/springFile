package com.files.files.service;

import com.files.files.model.File;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Nerses
 * Date: 2/17/18.
 * Time: 11:04 AM.
 * To change this template use File | Settings | File Templates.
 */
public interface FileService {

    List<File> getAll();

    File save(File file);

    void deleteById(long id);

    File getById(long id);

}
