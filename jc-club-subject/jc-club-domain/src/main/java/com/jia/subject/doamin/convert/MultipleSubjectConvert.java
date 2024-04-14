package com.jia.subject.doamin.convert;

import com.jia.subject.doamin.entity.SubjectAnswerBO;
import com.jia.subject.doamin.entity.SubjectInfoBO;
import com.jia.subject.doamin.entity.SubjectOptionBO;
import com.jia.subject.infra.basic.entity.SubjectBrief;
import com.jia.subject.infra.basic.entity.SubjectMultiple;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MultipleSubjectConvert {

    MultipleSubjectConvert INSTANCE = Mappers.getMapper(MultipleSubjectConvert.class);

    SubjectMultiple subjectInfoBOTOSubjectMultiple(SubjectAnswerBO subjectAnswerBO);

    List<SubjectAnswerBO> subjectMutipleListTOSubjectAnswerList(List<SubjectMultiple> subjectMultipleList);
}
