package com.jia.subject.doamin.handle.subject;

import com.jia.subject.common.enums.SubjectInfoTypeEnum;
import com.jia.subject.doamin.entity.SubjectInfoBO;

public interface SubjectTypeHandle {

    //枚举身份的识别
    SubjectInfoTypeEnum getHandleType();

    //实际题目的插入
    void add(SubjectInfoBO subjectInfoBO);
}
