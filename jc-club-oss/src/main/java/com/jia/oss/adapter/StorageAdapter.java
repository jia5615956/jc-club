package com.jia.oss.adapter;

import com.jia.oss.entity.FileInfo;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

import java.util.List;


public interface StorageAdapter {
    //创造bucket桶
    public void createBucket(String bucket);

    //上传文件
    public void uploadFile(String bucketName, String fileName, MultipartFile inputStream);

    //列出所有的桶
    public List<String> getAllBucket();


    //列出当前桶及文件
    public List<FileInfo> getAllFile(String bucket);

    //下载文件
    public InputStream downloadFile(String bucketName, String fileName);

    //删除文件
    public void deleteFile(String bucketName, String fileName);

    //删除桶
    public void deleteBucket(String bucketName) ;

    String getUrl(String bucket, String objectName);
}
