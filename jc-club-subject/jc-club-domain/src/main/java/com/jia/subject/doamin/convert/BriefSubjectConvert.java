package com.jia.subject.doamin.convert;

import com.jia.subject.doamin.entity.SubjectInfoBO;
import com.jia.subject.infra.basic.entity.SubjectBrief;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BriefSubjectConvert {

    BriefSubjectConvert INSTANCE = Mappers.getMapper(BriefSubjectConvert.class);

    SubjectBrief subjectInfoBOTOSubjectBrief(SubjectInfoBO subjectInfoBO);
}
