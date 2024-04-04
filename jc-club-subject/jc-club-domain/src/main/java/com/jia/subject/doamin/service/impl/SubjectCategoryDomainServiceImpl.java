package com.jia.subject.doamin.service.impl;

import com.jia.subject.common.enums.IsDeletedFlagEnum;
import com.jia.subject.doamin.convert.SubjectCategoryConvert;
import com.jia.subject.doamin.entity.SubjectCategoryBO;
import com.jia.subject.doamin.service.SubjectCategoryDomainService;
import com.jia.subject.infra.basic.entity.SubjectCategory;
import com.jia.subject.infra.basic.service.SubjectCategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SubjectCategoryDomainServiceImpl implements SubjectCategoryDomainService {


    @Resource
    private SubjectCategoryService subjectCategoryService;

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
        List<SubjectCategory> categoryList = subjectCategoryService.queryCategory(subjectCategory);
        List<SubjectCategoryBO> boList = SubjectCategoryConvert.INSTANCE.convertListToCategoryBOList(categoryList);
        return boList;
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
}
