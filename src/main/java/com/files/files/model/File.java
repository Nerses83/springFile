package com.files.files.model;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Nerses
 * Date: 2/17/18.
 * Time: 10:59 AM.
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name = "files")
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "file_path")
    private String filePath;


    @Column(name = "file_name")
    private String fileName;


    public File() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
