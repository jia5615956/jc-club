package com.jia.subject.doamin.handle.subject;

import com.jia.subject.common.enums.SubjectInfoTypeEnum;
import com.jia.subject.doamin.convert.JudgeSubjectConvert;
import com.jia.subject.doamin.entity.SubjectAnswerBO;
import com.jia.subject.doamin.entity.SubjectInfoBO;
import com.jia.subject.doamin.entity.SubjectOptionBO;
import com.jia.subject.infra.basic.entity.SubjectJudge;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

//判断类
@Component
public class JudgeTypeHandle implements SubjectTypeHandle{
    @Override
    public SubjectInfoTypeEnum getHandleType() {
        return SubjectInfoTypeEnum.JUDGE;
    }

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        //获取是否正否正确
        SubjectAnswerBO subjectAnswerBO = subjectInfoBO.getOptionList().get(0);
        //转换


    }

    @Override
    public SubjectOptionBO query(Long subjectId) {
        return null;
    }
}
