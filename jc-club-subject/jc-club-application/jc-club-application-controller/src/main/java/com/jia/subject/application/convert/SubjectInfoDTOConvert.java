package com.jia.subject.application.convert;


import com.jia.subject.application.dto.SubjectInfoDTO;
import com.jia.subject.application.dto.SubjectLabelDTO;
import com.jia.subject.doamin.entity.SubjectInfoBO;
import com.jia.subject.doamin.entity.SubjectLabelBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubjectInfoDTOConvert {

    SubjectInfoDTOConvert INSTANCE = Mappers.getMapper(SubjectInfoDTOConvert.class);

    SubjectInfoBO subjectInfoDTOTOSubjectInfoBO(SubjectInfoDTO subjectInfoDTO);

}
