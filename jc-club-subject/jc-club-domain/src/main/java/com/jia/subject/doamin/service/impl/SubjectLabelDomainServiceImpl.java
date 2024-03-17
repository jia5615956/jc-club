package com.jia.subject.doamin.service.impl;

import com.jia.subject.common.enums.IsDeletedFlagEnum;
import com.jia.subject.doamin.convert.SubjectLabelConvert;
import com.jia.subject.doamin.entity.SubjectCategoryBO;
import com.jia.subject.doamin.entity.SubjectLabelBO;
import com.jia.subject.doamin.service.SubjectLabelDomainService;
import com.jia.subject.infra.basic.entity.SubjectLabel;
import com.jia.subject.infra.basic.service.SubjectLabelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class SubjectLabelDomainServiceImpl implements SubjectLabelDomainService {

    @Resource
    private SubjectLabelService subjectLabelService;

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
        //转换
        SubjectLabel subjectLabel = SubjectLabelConvert.INSTANCE.SubjectLabelBOTOSubjectLabel(subjectLabelBO);
        List<SubjectLabel> subjectLabels = subjectLabelService.queryLabel(subjectLabel);
        //转换
        List<SubjectLabelBO> subjectLabelBOList = SubjectLabelConvert.INSTANCE.SubjectLabelBOListTOSubjectLabelList(subjectLabels);
        return subjectLabelBOList;
    }
}
