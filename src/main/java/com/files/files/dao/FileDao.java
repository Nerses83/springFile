package com.files.files.dao;

import com.files.files.model.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created with IntelliJ IDEA.
 * User: Nerses
 * Date: 2/17/18.
 * Time: 11:09 AM.
 * To change this template use File | Settings | File Templates.
 */
@Transactional
public interface FileDao extends JpaRepository<File,Long> {
}
