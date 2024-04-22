package com.jia.subject.doamin.service.impl;

import com.jia.subject.common.enums.CategoryTypeEnum;
import com.jia.subject.common.enums.IsDeletedFlagEnum;
import com.jia.subject.doamin.convert.SubjectLabelConvert;
import com.jia.subject.doamin.entity.SubjectCategoryBO;
import com.jia.subject.doamin.entity.SubjectLabelBO;
import com.jia.subject.doamin.service.SubjectLabelDomainService;
import com.jia.subject.infra.basic.entity.SubjectCategory;
import com.jia.subject.infra.basic.entity.SubjectLabel;
import com.jia.subject.infra.basic.entity.SubjectMapping;
import com.jia.subject.infra.basic.service.SubjectCategoryService;
import com.jia.subject.infra.basic.service.SubjectLabelService;
import com.jia.subject.infra.basic.service.SubjectMappingService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class SubjectLabelDomainServiceImpl implements SubjectLabelDomainService {

    @Resource
    private SubjectLabelService subjectLabelService;

    @Resource
    private SubjectMappingService subjectMappingService;

    @Resource
    private SubjectCategoryService subjectCategoryService;

    @Override
    public void add(SubjectLabelBO subjectLabelBO) {
        //转换为BO
        SubjectLabel subjectLabel = SubjectLabelConvert.INSTANCE.SubjectLabelBOTOSubjectLabel(subjectLabelBO);
        //新增时将是否删除设置为未删除
        subjectLabel.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        subjectLabelService.insert(subjectLabel);
    }

    @Override
    public Boolean update(SubjectLabelBO subjectLabelBO) {
        //转换
        SubjectLabel subjectLabel = SubjectLabelConvert.INSTANCE.SubjectLabelBOTOSubjectLabel(subjectLabelBO);
        //调用服务
        int update = subjectLabelService.update(subjectLabel);
        return update > 0;
    }

    @Override
    public Boolean deleteLabel(SubjectLabelBO subjectLabelBO) {
        //转换
        SubjectLabel subjectLabel = SubjectLabelConvert.INSTANCE.SubjectLabelBOTOSubjectLabel(subjectLabelBO);
        //将是否删除的状态更改
        subjectLabel.setIsDeleted(IsDeletedFlagEnum.DELETED.code);
        int count = subjectLabelService.update(subjectLabel);
        return count > 0;
    }

    @Override
    public List<SubjectLabelBO> queryLabelByCategoryId(SubjectLabelBO subjectLabelBO) {
        //如果当前分类是1级分类，则查询所有标签
        SubjectCategory subjectCategory = subjectCategoryService.queryById(subjectLabelBO.getCategoryId());
        //判断
        if(CategoryTypeEnum.PRIMARY.getCode() == subjectCategory.getCategoryType()){
            //一级分类
            SubjectLabel subjectLabel = new SubjectLabel();
            subjectLabel.setCategoryId(subjectLabelBO.getCategoryId());
            List<SubjectLabel> subjectLabelList =subjectLabelService.queryByCondition(subjectLabel);
            //转换
            List<SubjectLabelBO> subjectLabelBOS = SubjectLabelConvert.INSTANCE.SubjectLabelListTOSubjectLabelBOList(subjectLabelList);
            return subjectLabelBOS;
        }
        //获取CategoryId
        Long categoryId = subjectLabelBO.getCategoryId();
        //新建对象
        SubjectMapping subjectMapping = new SubjectMapping();
        subjectMapping.setCategoryId(categoryId);
        subjectMapping.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        //先去映射表中差是否存在
        List<SubjectMapping> subjectMappingList = subjectMappingService.queryLabelId(subjectMapping);
        //判断
        if(CollectionUtils.isEmpty(subjectMappingList)){
            return Collections.emptyList();
        }
        //通过上面拿到的subjectMappingList遍历里面的labelId
        List<Long> labelIdList = subjectMappingList.stream().map(SubjectMapping::getLabelId).collect(Collectors.toList());
        //开始循环查找
        List<SubjectLabel> subjectLabelList = subjectLabelService.batchQueryById(labelIdList);
        //遍历，将查询到的subjectLabelList封装到subjectLabelBO
        LinkedList<SubjectLabelBO> labelBOLinkedList = new LinkedList<>();
        subjectLabelList.forEach(label->{
            SubjectLabelBO labelBO = new SubjectLabelBO();
            labelBO.setLabelName(label.getLabelName());
            labelBO.setCategoryId(label.getCategoryId());
            labelBO.setSortNum(label.getSortNum());
            labelBO.setId(label.getId());
            labelBOLinkedList.add(labelBO);
        });

        return labelBOLinkedList;
    }
}
