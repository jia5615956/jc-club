package com.jia.subject.application.controller;


import com.jia.subject.application.convert.SubjectCategoryDTOConverter;
import com.jia.subject.application.dto.SubjectCategoryDTO;
import com.jia.subject.common.entity.Result;

import com.jia.subject.doamin.entity.SubjectCategoryBO;
import com.jia.subject.doamin.service.SubjectCategoryDomainService;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/subject/category")
public class SubjectCategoryController {

    @Resource
    private SubjectCategoryDomainService subjectCategoryDomainService;

    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody SubjectCategoryDTO subjectCategoryDTO){
        try {
            //现将DTO转换为BO
            SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConverter.INSTANCE.convertDTOToCategoryBO(subjectCategoryDTO);
            subjectCategoryDomainService.add(subjectCategoryBO);
            return Result.ok(true);
        }catch (Exception e){
            return Result.fail();
        }


    }
}
