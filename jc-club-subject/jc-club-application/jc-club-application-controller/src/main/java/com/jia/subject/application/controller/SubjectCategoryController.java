package com.jia.subject.application.controller;


import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.jia.subject.application.convert.SubjectCategoryDTOConverter;
import com.jia.subject.application.dto.SubjectCategoryDTO;
import com.jia.subject.common.entity.Result;
import com.jia.subject.doamin.convert.SubjectCategoryConvert;
import com.jia.subject.doamin.entity.SubjectCategoryBO;
import com.jia.subject.doamin.service.SubjectCategoryDomainService;
import com.jia.subject.infra.basic.entity.SubjectCategory;
import com.mysql.cj.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/subject/category")
@Slf4j
public class SubjectCategoryController {

    @Resource
    private SubjectCategoryDomainService subjectCategoryDomainService;


    //添加分类
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody SubjectCategoryDTO subjectCategoryDTO){
        try {
            Preconditions.checkNotNull(subjectCategoryDTO.getCategoryType(),"分类类型不能为空");
            Preconditions.checkArgument(StringUtils.isNullOrEmpty(subjectCategoryDTO.getCategoryName()),"分类名字不能为空");
            Preconditions.checkNotNull(subjectCategoryDTO.getParentId(),"父id不能为空");
            //现将DTO转换为BO
            SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConverter.INSTANCE.convertDTOToCategoryBO(subjectCategoryDTO);
            subjectCategoryDomainService.add(subjectCategoryBO);
            return Result.ok(true);
        }catch (Exception e){
            log.info("SubjectCategoryController.add.error:{}",e.getMessage(),e);
            return Result.fail(e.getMessage());
        }
    }

    //查询分类
    @GetMapping("/queryPrimaryCategory")
    public Result<List<SubjectCategoryDTO>> queryPrimaryCategory(){
        try{
            //为了同一个服务调用
            SubjectCategoryBO subjectCategoryBO = new SubjectCategoryBO();
            //查询
            List<SubjectCategoryBO> boList = subjectCategoryDomainService.queryCategory(subjectCategoryBO);
            //转换
            List<SubjectCategoryDTO> subjectCategoryDTOList = SubjectCategoryDTOConverter.INSTANCE.convertBOListToDTOList(boList);
            return Result.ok(subjectCategoryDTOList);
        }catch (Exception e){
            log.info("SubjectCategoryController.add.error:{}",e.getMessage(),e);
            return Result.fail(e.getMessage());
        }
    }

    //根据主键查询
    @PostMapping("/queryCategoryByPrimary")
    public Result<SubjectCategory> queryCategoryByPrimary(@RequestBody SubjectCategoryDTO subjectCategoryDTO){
        try{
            if(log.isInfoEnabled()){
                log.info("SubjectCategoryController.queryCategoryByPrimary.dto:{}", JSON.toJSONString(subjectCategoryDTO));
            }
            //判断
            Preconditions.checkNotNull(subjectCategoryDTO.getParentId(),"分类父Id不能为空");
            //将DTO转为BO
            SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConverter.INSTANCE.convertDTOToCategoryBO(subjectCategoryDTO);
            //调用服务
            List<SubjectCategoryBO> boList = subjectCategoryDomainService.queryCategory(subjectCategoryBO);
            return Result.ok(boList);
        }catch (Exception e){
            log.info("SubjectCategoryController.add.error:{}",e.getMessage(),e);
            return Result.fail(e.getMessage());
        }
    }
}
