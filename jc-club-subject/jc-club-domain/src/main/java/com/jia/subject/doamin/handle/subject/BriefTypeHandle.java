package com.jia.subject.doamin.handle.subject;

import com.jia.subject.common.enums.IsDeletedFlagEnum;
import com.jia.subject.common.enums.SubjectInfoTypeEnum;
import com.jia.subject.doamin.convert.BriefSubjectConvert;
import com.jia.subject.doamin.entity.SubjectAnswerBO;
import com.jia.subject.doamin.entity.SubjectInfoBO;
import com.jia.subject.doamin.entity.SubjectOptionBO;
import com.jia.subject.infra.basic.entity.SubjectBrief;
import com.jia.subject.infra.basic.service.SubjectBriefService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

//简答题类
@Component
public class BriefTypeHandle implements SubjectTypeHandle{

    @Resource
    private SubjectBriefService subjectBriefService;

    @Override
    public SubjectInfoTypeEnum getHandleType() {
        return SubjectInfoTypeEnum.BRIEF;
    }

    //插入简答题选项和答案
    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        //获取简单题答案
        SubjectBrief subjectBrief = BriefSubjectConvert.INSTANCE.subjectInfoBOTOSubjectBrief(subjectInfoBO);
        //将subjectInfoid插入
        subjectBrief.setSubjectId(subjectInfoBO.getId());
        //未删除标识插入
        subjectBrief.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        //调用服务插入答案
        subjectBriefService.insert(subjectBrief);
    }

    @Override
    public SubjectOptionBO query(Long subjectId) {
        SubjectBrief subjectBrief = new SubjectBrief();
        subjectBrief.setSubjectId(subjectId);
        SubjectBrief result = subjectBriefService.queryByCondition(subjectBrief);
        SubjectOptionBO subjectOptionBO = new SubjectOptionBO();
        subjectOptionBO.setSubjectAnswer(result.getSubjectAnswer());
        return subjectOptionBO;

    }
}
