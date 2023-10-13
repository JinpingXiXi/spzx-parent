package com.atguigu.spzx.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.atguigu.spzx.properties.MinioProperties;
import com.atguigu.spzx.service.FileService;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Date;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private MinioProperties minioProperties;

    @Override
    public String upload(MultipartFile file) throws Exception {
        // 创建minio客户端对象，连接服务端
        MinioClient minioClient = MinioClient.builder()
                .endpoint(minioProperties.getEndpoint())
                .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                .build();

        //需要上传的文件
        InputStream inputStream = file.getInputStream();

        //保存在minio上的名称： 2023-10-12/uuid+原文件名
        String date = DateUtil.format(new Date(), "yyyy-MM-dd/");
        String finalName = date + IdUtil.simpleUUID() + file.getOriginalFilename();

        // 上传文件
        minioClient.putObject(PutObjectArgs.builder()
                .bucket(minioProperties.getBucket()) //桶的名称
                .object(finalName) //保存在minio上的名称
                .stream(inputStream, inputStream.available(), -1) //文件流
                .build());

        //返回文件地址
        return minioProperties.getEndpoint() + "/" +
                minioProperties.getBucket() + "/" +
                finalName;
    }
}
