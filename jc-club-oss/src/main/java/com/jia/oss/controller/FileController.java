package com.jia.oss.controller;

import com.jia.oss.util.MinioUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class FileController {

    @Resource
    private MinioUtil minioUtil;

    @RequestMapping("/testGetAllBucjet")
    public String testGetAllBucjet()throws Exception{
        List<String> allBucket = minioUtil.getAllBucket();
        return allBucket.get(0);
    } 
}
