package com.ego.manager.controller;

import com.ego.common.result.FileResult;
import com.ego.manager.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Author: sun
 * @DateTime: 2019/12/26 21:10
 * @since 1.0.0
 */
@Controller
@RequestMapping("fileUpload")
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @RequestMapping("save")
    @ResponseBody
    public FileResult upload(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String date = DateTimeFormatter.ofPattern("yyyy/MM/dd").format(LocalDateTime.now());
        fileName = date + System.currentTimeMillis() + fileName.substring(fileName.lastIndexOf("."));
        return uploadService.upload(file.getInputStream(), fileName);
    }

}
