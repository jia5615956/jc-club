package com.jia.subject.doamin.handle.subject;

import com.jia.subject.common.enums.SubjectInfoTypeEnum;
import com.jia.subject.doamin.entity.SubjectInfoBO;



//单选的题目策略类
public class RadioTypeHandle implements SubjectTypeHandle{
    @Override
    public SubjectInfoTypeEnum getHandleType() {
        return SubjectInfoTypeEnum.RADIO;
    }

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        //单选题目的插入
    }
}
