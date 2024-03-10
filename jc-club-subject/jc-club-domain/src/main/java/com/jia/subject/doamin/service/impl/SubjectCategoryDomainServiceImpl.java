package com.jia.subject.doamin.service.impl;

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
        subjectCategoryService.insert(subjectCategory);
    }

    //查询分类
    @Override
    public List<SubjectCategoryBO> queryPrimaryCategory() {
        //转换
        List<SubjectCategory> categoryList = subjectCategoryService.queryPrimaryCategory();
        List<SubjectCategoryBO> boList = SubjectCategoryConvert.INSTANCE.convertListToCategoryBOList(categoryList);
        return boList;
    }
}
