package com.jia.oss.config;


import com.jia.oss.adapter.AliStorageAdapter;
import com.jia.oss.adapter.MinioStorageAdapter;
import com.jia.oss.adapter.StorageAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class StorageConfig {
    @Value("${storage.service.type}")
    private String storageType;

    @Bean
    public StorageAdapter getStorageService() {
        if("minio".equals(storageType)) {
            return new MinioStorageAdapter();
        }else if("ali".equals(storageType)) {
            return new AliStorageAdapter();
        }else {
            throw  new IllegalArgumentException("未找到对应的文件存储处理器");
        }
    }
}
