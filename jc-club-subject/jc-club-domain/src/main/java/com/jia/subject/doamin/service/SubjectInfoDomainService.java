package com.jia.subject.doamin.service;

import com.jia.subject.common.entity.PageResult;
import com.jia.subject.doamin.entity.SubjectInfoBO;

public interface SubjectInfoDomainService {
    public void add(SubjectInfoBO subjectInfoBO);

    //查询题目详情
    SubjectInfoBO querySubjectInfo(SubjectInfoBO subjectInfoBO);

    PageResult<SubjectInfoBO> getSubjectPage(SubjectInfoBO subjectInfoBO);
}
