package com.jia.oss.service;

import com.jia.oss.adapter.StorageAdapter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;



import java.util.List;


@Service
public class FileService {

    private final StorageAdapter storageAdapter;

    public FileService(StorageAdapter storageAdapter) {
        this.storageAdapter = storageAdapter;
    }


    //列出所有桶
   public List<String> getAllBucket(){
      return storageAdapter.getAllBucket();
    }

}
