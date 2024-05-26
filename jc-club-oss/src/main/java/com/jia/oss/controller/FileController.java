package com.jia.oss.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.jia.oss.service.FileService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class FileController {

    @Resource
    private FileService fileService;

    @NacosValue(value = "${storage.service.type}",autoRefreshed = true)
    private String storageType;


    @RequestMapping("/testGetAllBucjet")
    public String testGetAllBucjet()throws Exception{
        List<String> allBucket = fileService.getAllBucket();
        return allBucket.get(0);
    }

    //获取url
    @RequestMapping("/getUrl")
    public String getUrl(String bucketName,String objectName)throws Exception{
        return fileService.getUrl(bucketName,objectName);
    }

    //上传文件
    @RequestMapping("/upload")
    public String upload(String bucketName, String fileName, MultipartFile inputStream)throws Exception{
        return fileService.uploadFile(bucketName, fileName, inputStream);
    }
}
