package com.jia.subject.application.controller;


import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.jia.subject.application.convert.SubjectCategoryDTOConverter;
import com.jia.subject.application.convert.SubjectLabelDTOConvert;
import com.jia.subject.application.dto.SubjectCategoryDTO;
import com.jia.subject.application.dto.SubjectLabelDTO;
import com.jia.subject.common.entity.Result;
import com.jia.subject.doamin.entity.SubjectCategoryBO;
import com.jia.subject.doamin.service.SubjectCategoryDomainService;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.LinkedList;
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
            if(log.isInfoEnabled()){
                log.info("SubjectCategoryController.add.dto:{}", JSON.toJSONString(subjectCategoryDTO));
            }
            Preconditions.checkNotNull(subjectCategoryDTO.getCategoryType(),"分类类型不能为空");
            Preconditions.checkArgument(!StringUtils.isBlank(subjectCategoryDTO.getCategoryName()),"分类名字不能为空");
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

    //查询岗位大类
    @PostMapping("/queryPrimaryCategory")
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
            return Result.fail("查询失败");
        }
    }

    //根据主键查询
    @PostMapping("/queryCategoryByPrimary")
    public Result<SubjectCategoryDTO> queryCategoryByPrimary(@RequestBody SubjectCategoryDTO subjectCategoryDTO){
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
            //转换
            List<SubjectCategoryDTO> subjectCategoryDTOS = SubjectCategoryDTOConverter.INSTANCE.convertBOListToDTOList(boList);
            return Result.ok(subjectCategoryDTOS);
        }catch (Exception e){
            log.info("SubjectCategoryController.add.error:{}",e.getMessage(),e);
            return Result.fail(e.getMessage());
        }
    }

    //查询岗位大类及其下的标签
    @PostMapping("/queryCategoryAndLabel")
    public Result<List<SubjectCategoryDTO>> queryCategoryAndLabel(@RequestBody SubjectCategoryDTO subjectCategoryDTO){
        try {
            if(log.isInfoEnabled()){
                log.info("SubjectCategoryController.queryCategoryAndLabel.dto:{}", JSON.toJSONString(subjectCategoryDTO));
            }
            //判断id不能为空
            Preconditions.checkNotNull(subjectCategoryDTO.getId(),"分类Id不能为空");
            //转换
            SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConverter.INSTANCE.convertDTOToCategoryBO(subjectCategoryDTO);
            //调用服务查询
            List<SubjectCategoryBO> subjectCategoryBOList = subjectCategoryDomainService.queryCategoryAndLabel(subjectCategoryBO);
            List<SubjectCategoryDTO> categoryDTOList = new LinkedList<>();
            subjectCategoryBOList.forEach(bo->{
                //转换
                SubjectCategoryDTO categoryDTO = SubjectCategoryDTOConverter.INSTANCE.convertCategoryBOToDTO(bo);
                List<SubjectLabelDTO> subjectLabelDTOS = SubjectLabelDTOConvert.INSTANCE.SubjectLabelBOListTOSubjectLabelDTO(bo.getSubjectLabelBOList());
                categoryDTO.setSubjectLabelDTOOList(subjectLabelDTOS);
                categoryDTOList.add(categoryDTO);
            });

            return Result.ok(categoryDTOList);
        }catch (Exception e){
            log.info("SubjectCategoryController.add.error:{}",e.getMessage(),e);
            return Result.fail(e.getMessage());
        }
    }

    //删除，删除并不是真正的删除没事逻辑删除，修改字段为已删除，数据还在
    @PostMapping("/deleteCategoryByPrimary")
    public Result<Boolean> deleteCategoryByPrimary(@RequestBody SubjectCategoryDTO subjectCategoryDTO){
        try{
            if(log.isInfoEnabled()){
                log.info("SubjectCategoryController.deleteCategoryByPrimary.dto:{}", JSON.toJSONString(subjectCategoryDTO));
            }
            //判断，id不为空
            Preconditions.checkNotNull(subjectCategoryDTO.getId(),"id不能为空");
            //将DTO转换为BO
            SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConverter.INSTANCE.convertDTOToCategoryBO(subjectCategoryDTO);
            //调用服务
            Boolean flag = subjectCategoryDomainService.deleteCategoryByPrimary(subjectCategoryBO);
            return Result.ok(flag);
        }catch (Exception e){
            log.info("SubjectCategoryController.add.error:{}",e.getMessage(),e);
            return Result.fail(e.getMessage());
        }
    }

    //更新
    @PostMapping("/updateCategory")
    public Result<Boolean> updateCategory(@RequestBody SubjectCategoryDTO subjectCategoryDTO){
        try{
            if(log.isInfoEnabled()){
                log.info("SubjectCategoryController.updateCategory.dto:{}", JSON.toJSONString(subjectCategoryDTO));
            }
            //判断
            Preconditions.checkNotNull(subjectCategoryDTO.getCategoryType(),"分类类型不能为空");
            Preconditions.checkArgument(!StringUtils.isBlank(subjectCategoryDTO.getCategoryName()),"分类名字不能为空");
            Preconditions.checkNotNull(subjectCategoryDTO.getParentId(),"父id不能为空");
            Preconditions.checkNotNull(subjectCategoryDTO.getId(),"id不能为空");
            //转换
            SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConverter.INSTANCE.convertDTOToCategoryBO(subjectCategoryDTO);
            //调用服务
            Boolean flag = subjectCategoryDomainService.updateCategory(subjectCategoryBO);
            return Result.ok(flag);
        }catch (Exception e){
            log.info("SubjectCategoryController.add.error:{}",e.getMessage(),e);
            return Result.fail(e.getMessage());
        }
    }

}
