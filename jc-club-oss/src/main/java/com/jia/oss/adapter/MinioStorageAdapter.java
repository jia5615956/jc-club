package com.jia.oss.adapter;

import com.jia.oss.entity.FileInfo;
import com.jia.oss.util.MinioUtil;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import javax.annotation.Resource;
import java.io.InputStream;

import java.util.List;

public class MinioStorageAdapter implements StorageAdapter {

    @Resource
    private MinioUtil minioUtil;

    /**
     * minioUrl
     */
    @Value("${minio.url}")
    private String url;


    @Override
    @SneakyThrows
    public void createBucket(String bucket){
        minioUtil.createBucket(bucket);
    }

    @Override
    @SneakyThrows
    public void uploadFile(MultipartFile uploadFile,String bucket,String objectName){
        //先创建bucket
        minioUtil.createBucket(bucket);
        //判断文件名字是否存在
        if (objectName != null) {
            minioUtil.uploadFile(uploadFile.getInputStream(), bucket, objectName + "/" + uploadFile.getOriginalFilename());
        } else {
            minioUtil.uploadFile(uploadFile.getInputStream(), bucket, uploadFile.getOriginalFilename());
        }

    }

    @Override
    @SneakyThrows
    public List<String> getAllBucket(){
        return minioUtil.getAllBucket();
    }

    @Override
    @SneakyThrows
    public List<FileInfo> getAllFile(String bucket){
        return minioUtil.getAllFile(bucket);
    }

    @Override
    @SneakyThrows
    public InputStream downloadFile(String bucketName, String fileName){
        return minioUtil.downloadFile(bucketName,fileName);
    }

    @Override
    @SneakyThrows
    public void deleteFile(String bucketName, String fileName){
        minioUtil.deleteFile(bucketName,fileName);
    }

    @Override
    @SneakyThrows
    public void deleteBucket(String bucketName){
        minioUtil.deleteBucket(bucketName);
    }

    @Override
    @SneakyThrows
    public String getUrl(String bucket, String objectName) {
        return minioUtil.getPreviewFileUrl(bucket,objectName);
    }
}
