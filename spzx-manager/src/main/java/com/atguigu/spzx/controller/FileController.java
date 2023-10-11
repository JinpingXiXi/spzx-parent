package com.atguigu.spzx.controller;

import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/admin/system/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file){
        String fileUrl = fileService.upload(file);
        return Result.ok(fileUrl);
    }
}
