package com.jia.oss.adapter;

import com.jia.oss.entity.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public class AliStorageAdapter implements StorageAdapter {
    @Override
    public void createBucket(String bucket) {

    }

    @Override
    public void uploadFile(String bucketName, String fileName, MultipartFile inputStream){

    }

    @Override
    public List<String> getAllBucket(){
        LinkedList<String> bucketNameList = new LinkedList<>();
        bucketNameList.add("aliyun");
        return bucketNameList;
    }

    @Override
    public List<FileInfo> getAllFile(String bucket){
        return Collections.emptyList();
    }

    @Override
    public InputStream downloadFile(String bucketName, String fileName){
        return null;
    }

    @Override
    public void deleteFile(String bucketName, String fileName){

    }

    @Override
    public void deleteBucket(String bucketName) {

    }

    @Override
    public String getUrl(String bucket, String objectName) {
        return null;
    }
}
