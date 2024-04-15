package com.jia.subject.doamin.convert;

import com.jia.subject.doamin.entity.SubjectAnswerBO;
import com.jia.subject.doamin.entity.SubjectOptionBO;
import com.jia.subject.infra.basic.entity.SubjectInfo;
import com.jia.subject.infra.basic.entity.SubjectRadio;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RadioSubjectConvert {

    RadioSubjectConvert INSTANCE = Mappers.getMapper(RadioSubjectConvert.class);

    SubjectRadio convertBoToEntity(SubjectAnswerBO subjectAnswerBO);

    List<SubjectAnswerBO> subjectRadioListTOSubjectAnswerBO(List<SubjectRadio> subjectRadioList);
}
