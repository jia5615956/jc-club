package com.jia.subject.doamin.service;

import com.jia.subject.doamin.entity.SubjectCategoryBO;
import com.jia.subject.infra.basic.entity.SubjectCategory;


import java.util.List;


public interface SubjectCategoryDomainService {


    //新增分类
    public void add (SubjectCategoryBO subjectCategoryBO);

    //查询分类
    List<SubjectCategoryBO> queryCategory(SubjectCategoryBO subjectCategoryBO);

    //删除
    Boolean deleteCategoryByPrimary(SubjectCategoryBO subjectCategoryBO);

    Boolean updateCategory(SubjectCategoryBO subjectCategoryBO);

    List<SubjectCategoryBO> queryCategoryAndLabel(SubjectCategoryBO subjectCategoryBO);
}
