package com.jia.subject.doamin.handle.subject;

import com.jia.subject.common.enums.SubjectInfoTypeEnum;
import com.jia.subject.doamin.entity.SubjectInfoBO;
import com.jia.subject.doamin.entity.SubjectOptionBO;
import com.jia.subject.infra.basic.entity.SubjectBrief;
import com.jia.subject.infra.basic.service.SubjectBriefService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

//简答题类
@Component
public class BriefTypeHandle implements SubjectTypeHandle{

    @Resource
    private SubjectBriefService subjectBriefService;

    @Override
    public SubjectInfoTypeEnum getHandleType() {
        return SubjectInfoTypeEnum.BRIEF;
    }

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {

    }

    @Override
    public SubjectOptionBO query(Long subjectId) {
        SubjectBrief subjectBrief = subjectBriefService.queryById(subjectId);
        SubjectOptionBO subjectOptionBO = new SubjectOptionBO();
        subjectOptionBO.setSubjectAnswer(subjectBrief.getSubjectAnswer());
        return subjectOptionBO;
    }
}
