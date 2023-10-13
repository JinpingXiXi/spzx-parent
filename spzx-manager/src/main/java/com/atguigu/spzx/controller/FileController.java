package com.atguigu.spzx.controller;

import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "文件接口")
@RestController
@RequestMapping("/admin/system/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @Operation(summary = "上传文件")
    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) throws Exception {
        //上传文件后，响应地址给前端
        String url = fileService.upload(file);
        return Result.ok(url);
    }
}
