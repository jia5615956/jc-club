package com.jia.subject.doamin.service.impl;

import com.jia.subject.common.enums.IsDeletedFlagEnum;
import com.jia.subject.doamin.convert.SubjectLabelConvert;
import com.jia.subject.doamin.entity.SubjectCategoryBO;
import com.jia.subject.doamin.entity.SubjectLabelBO;
import com.jia.subject.doamin.service.SubjectLabelDomainService;
import com.jia.subject.infra.basic.entity.SubjectLabel;
import com.jia.subject.infra.basic.entity.SubjectMapping;
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
