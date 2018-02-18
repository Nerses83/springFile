package com.files.files.controller;

import com.files.files.model.AjaxResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.sun.org.apache.xerces.internal.util.PropertyState.is;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

/**
 * Created with IntelliJ IDEA.
 * User: Nerses
 * Date: 2/18/18.
 * Time: 11:13 AM.
 * To change this template use File | Settings | File Templates.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FileRestControllerTest {

    private final static String UPLOADED_FOLDER = "/home/nerses/tmp/";


    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void upload(){
        MultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<>();
        bodyMap.add("file", getUserFileResource("151885kkk9910564dropped text.txt"));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(bodyMap, headers);

        ResponseEntity<AjaxResponse> response = this.restTemplate.exchange("/file",
                HttpMethod.POST, requestEntity, AjaxResponse.class);
        assertEquals(response.getBody().getMsg(),"success");
    }

    public static Resource getUserFileResource(String fileStoredName){
        //todo replace tempFile with a real file
        Path path = Paths.get(UPLOADED_FOLDER + fileStoredName);
        File file = path.toFile();
        //to upload in-memory bytes use ByteArrayResource instead
        return new FileSystemResource(file);
    }

    @Test
    public void update() {
    }

    @Test
    public void deleteFile() {
    }

    @Test
    public void getFile() {
        AjaxResponse<?> body = this.restTemplate.getForObject("/file/6", AjaxResponse.class);
        assertEquals(body.getMsg(),"success");
    }
}