package com.jia.subject.doamin.convert;

import com.jia.subject.doamin.entity.SubjectAnswerBO;
import com.jia.subject.doamin.entity.SubjectInfoBO;
import com.jia.subject.infra.basic.entity.SubjectBrief;
import com.jia.subject.infra.basic.entity.SubjectJudge;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface JudgeSubjectConvert {

    JudgeSubjectConvert INSTANCE = Mappers.getMapper(JudgeSubjectConvert.class);

    List<SubjectAnswerBO> subjectJudgeListTOSubjectAnswerBOList(List<SubjectJudge> subjectJudges);

}
