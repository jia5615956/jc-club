package com.jia.subject.doamin.service.impl;

import com.alibaba.fastjson.JSON;
import com.jia.subject.common.enums.IsDeletedFlagEnum;
import com.jia.subject.doamin.convert.SubjectCategoryConvert;
import com.jia.subject.doamin.entity.SubjectCategoryBO;
import com.jia.subject.doamin.entity.SubjectLabelBO;
import com.jia.subject.doamin.service.SubjectCategoryDomainService;
import com.jia.subject.infra.basic.entity.SubjectCategory;
import com.jia.subject.infra.basic.entity.SubjectLabel;
import com.jia.subject.infra.basic.entity.SubjectMapping;
import com.jia.subject.infra.basic.service.SubjectCategoryService;
import com.jia.subject.infra.basic.service.SubjectLabelService;
import com.jia.subject.infra.basic.service.SubjectMappingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SubjectCategoryDomainServiceImpl implements SubjectCategoryDomainService {


    @Resource
    private SubjectCategoryService subjectCategoryService;

    @Resource
    private SubjectMappingService subjectMappingService;

    @Resource
    private SubjectLabelService subjectLabelService;

    //新增分类
    @Override
    public void add(SubjectCategoryBO subjectCategoryBO) {
        //将BO转换为SubjectCategory
        SubjectCategory subjectCategory = SubjectCategoryConvert.INSTANCE.convertBoToCategory(subjectCategoryBO);
        //将是否删除的值赋值
        subjectCategory.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());

        subjectCategoryService.insert(subjectCategory);
    }

    //查询分类
    @Override
    public List<SubjectCategoryBO> queryCategory(SubjectCategoryBO subjectCategoryBO) {
        //将BO转换
        SubjectCategory subjectCategory = SubjectCategoryConvert.INSTANCE.convertBoToCategory(subjectCategoryBO);
        subjectCategory.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        List<SubjectCategory> categoryList = subjectCategoryService.queryCategory(subjectCategory);
        List<SubjectCategoryBO> subjectCategoryList = SubjectCategoryConvert.INSTANCE.convertListToCategoryBOList(categoryList);
        if(log.isInfoEnabled()){
            log.info("SubjectCategoryController.queryCategoryAndLabel.subjectCategoryList:{}",
                    JSON.toJSONString(subjectCategoryList));

        }
        return subjectCategoryList;
    }


    //删除
    @Override
    public Boolean deleteCategoryByPrimary(SubjectCategoryBO subjectCategoryBO) {
        //转换
        SubjectCategory subjectCategory = SubjectCategoryConvert.INSTANCE.convertBoToCategory(subjectCategoryBO);
        //调用更新服务
        subjectCategory.setIsDeleted(IsDeletedFlagEnum.DELETED.code);
        int count = subjectCategoryService.update(subjectCategory);
        return count > 0;
    }

    @Override
    public Boolean updateCategory(SubjectCategoryBO subjectCategoryBO) {
        //转换
        SubjectCategory subjectCategory = SubjectCategoryConvert.INSTANCE.convertBoToCategory(subjectCategoryBO);
        //调用服务
        int count = subjectCategoryService.update(subjectCategory);
        return count > 0;
    }

    @Override
    public List<SubjectCategoryBO> queryCategoryAndLabel(SubjectCategoryBO subjectCategoryBO) {
        //根据id查询其下分类
        SubjectCategory subjectCategory = new SubjectCategory();
        subjectCategory.setParentId(subjectCategoryBO.getId());
        subjectCategory.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        List<SubjectCategory> subjectCategories = subjectCategoryService.queryCategory(subjectCategory);
        //转换
        List<SubjectCategoryBO> subjectCategoryBOList = SubjectCategoryConvert.INSTANCE.convertListToCategoryBOList(subjectCategories);
        //遍历分类下的标签
        subjectCategoryBOList.forEach(category->{
            SubjectMapping subjectMapping = new SubjectMapping();
            subjectMapping.setSubjectId(category.getId());
            subjectMapping.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
            List<SubjectMapping> mappingList = subjectMappingService.queryLabelId(subjectMapping);
            //判断是否为空
            if(CollectionUtils.isEmpty(mappingList)){
                return;
            }
            //获取labelId
            List<Long> labelIds = mappingList.stream().map(SubjectMapping::getLabelId).collect(Collectors.toList());
            List<SubjectLabel> subjectLabelList = subjectLabelService.batchQueryById(labelIds);
            List<SubjectLabelBO> subjectLabelBOList = new LinkedList<>();
            //遍历通过id查询
            subjectLabelList.forEach(label->{
                SubjectLabelBO subjectLabelBO = new SubjectLabelBO();
                subjectLabelBO.setId(label.getId());
                subjectLabelBO.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
                subjectLabelBO.setCategoryId(label.getCategoryId());
                subjectLabelBO.setLabelName(label.getLabelName());
                subjectLabelBO.setSortNum(label.getSortNum());
                subjectLabelBOList.add(subjectLabelBO);
            });
            category.setSubjectLabelBOList(subjectLabelBOList);
        });
        return subjectCategoryBOList;
    }
}
