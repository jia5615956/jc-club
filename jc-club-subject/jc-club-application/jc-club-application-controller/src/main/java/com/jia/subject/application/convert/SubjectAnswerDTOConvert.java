package com.jia.subject.application.convert;


import com.jia.subject.application.dto.SubjectAnswerDTO;
import com.jia.subject.doamin.entity.SubjectAnswerBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectAnswerDTOConvert {

    SubjectAnswerDTOConvert INSTANCE = Mappers.getMapper(SubjectAnswerDTOConvert.class);

    List<SubjectAnswerBO> subjectAnswerDTOTOSubjectAnswerBO(List<SubjectAnswerDTO> subjectAnswerDTOList);

}
