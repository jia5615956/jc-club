package com.jia.oss.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
//minio配置文件
@Configuration
public class MinioConfig {

    @Value("{minio.url}")
    private String url;

    @Value("{minio.accesskey}")
    private String accesskey;

    @Value("{minio.secretkey}")
    private String secretkey;

    public MinioClient getMinioClient() {
        return MinioClient.builder().endpoint(url).credentials(accesskey, secretkey).build();
    }

}
