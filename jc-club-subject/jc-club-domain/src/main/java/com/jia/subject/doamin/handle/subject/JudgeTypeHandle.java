package com.jia.subject.doamin.handle.subject;

import com.jia.subject.common.enums.IsDeletedFlagEnum;
import com.jia.subject.common.enums.SubjectInfoTypeEnum;
import com.jia.subject.doamin.convert.JudgeSubjectConvert;
import com.jia.subject.doamin.entity.SubjectAnswerBO;
import com.jia.subject.doamin.entity.SubjectInfoBO;
import com.jia.subject.doamin.entity.SubjectOptionBO;
import com.jia.subject.infra.basic.entity.SubjectJudge;
import com.jia.subject.infra.basic.service.SubjectJudgeService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

//判断类
@Component
public class JudgeTypeHandle implements SubjectTypeHandle{

    @Resource
    private SubjectJudgeService subjectJudgeService;


    @Override
    public SubjectInfoTypeEnum getHandleType() {
        return SubjectInfoTypeEnum.JUDGE;
    }

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        SubjectJudge subjectJudge = new SubjectJudge();
        SubjectAnswerBO subjectAnswerBO = subjectInfoBO.getOptionList().get(0);
        subjectJudge.setSubjectId(subjectInfoBO.getId());
        subjectJudge.setIsCorrect(subjectAnswerBO.getIsCorrect());
        subjectJudge.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        //调用服务删除
        subjectJudgeService.insert(subjectJudge);
    }

    //查询判断题详情
    @Override
    public SubjectOptionBO query(Long subjectId) {
        SubjectJudge subjectJudge = new SubjectJudge();
        subjectJudge.setSubjectId(subjectId);
        List<SubjectJudge> subjectJudgeList = subjectJudgeService.queryByCondition(subjectJudge);
        //转换
        List<SubjectAnswerBO> subjectAnswerBOS = JudgeSubjectConvert.INSTANCE.subjectJudgeListTOSubjectAnswerBOList(subjectJudgeList);
        SubjectOptionBO subjectOptionBO = new SubjectOptionBO();
        subjectOptionBO.setOptionList(subjectAnswerBOS);
        return subjectOptionBO;
    }
}
