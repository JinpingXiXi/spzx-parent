package com.atguigu.spzx.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.atguigu.spzx.properties.MinioProperties;
import com.atguigu.spzx.service.FileService;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private MinioProperties minioProperties ;


    @Override
    public String upload(MultipartFile file) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {

        MinioClient minioClient = MinioClient.builder()
                .endpoint(minioProperties.getEndpointUrl())
                .credentials(minioProperties.getAccessKey(), minioProperties.getSecreKey())
                .build();
        boolean isBucketExist = minioClient.bucketExists(BucketExistsArgs.builder().bucket(minioProperties.getBucketName()).build());
        if (!isBucketExist) {
            minioClient.makeBucket((MakeBucketArgs.builder().build()));
        }else{
            System.out.println("Bucket 'spzx-bucket' already exists.");
        }

        String dateDir = DateUtil.format(new Date(),"yyyyMMdd");
        String uuid = IdUtil.simpleUUID();

        String fileName = dateDir + "/" + uuid + file.getOriginalFilename();
        System.out.println(fileName);

        PutObjectArgs putObjectArgsBuild = PutObjectArgs
                .builder()
                .bucket(minioProperties.getBucketName())
                .stream(file.getInputStream(), file.getSize(), -1)
                .object(fileName)
                .build();
        minioClient.putObject(putObjectArgsBuild);

        //file name looks like /20230801/443e1e772bef482c95be28704bec58a901.png
        return minioProperties.getEndpointUrl()
                + "/" + minioProperties.getBucketName()
                + "/" + fileName;


    }





}
