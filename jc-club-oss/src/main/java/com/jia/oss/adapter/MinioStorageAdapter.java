package com.jia.oss.adapter;

import com.jia.oss.entity.FileInfo;
import com.jia.oss.util.MinioUtil;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import javax.annotation.Resource;
import java.io.InputStream;

import java.util.List;
@Service("minioStorageServiceImpl")
public class MinioStorageAdapter implements StorageAdapter {

    @Resource
    private MinioUtil minioUtil;

    @Override
    @SneakyThrows
    public void createBucket(String bucket){
        minioUtil.createBucket(bucket);
    }

    @Override
    @SneakyThrows
    public void uploadFile(String bucketName, String fileName, MultipartFile uploadFile){
        //先创建bucket
        minioUtil.createBucket(bucketName);
        //判断文件名字是否存在
        if(fileName != null && !fileName.isEmpty()){
            minioUtil.uploadFile(uploadFile.getInputStream(),bucketName,fileName+"/"+uploadFile.getName());
        }else {
            minioUtil.uploadFile(uploadFile.getInputStream(),bucketName,uploadFile.getName());
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
}
