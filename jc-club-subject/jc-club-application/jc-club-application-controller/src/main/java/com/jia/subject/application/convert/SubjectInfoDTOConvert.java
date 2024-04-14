package com.jia.subject.application.convert;


import com.jia.subject.application.dto.SubjectInfoDTO;
import com.jia.subject.doamin.entity.SubjectInfoBO;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectInfoDTOConvert {

    SubjectInfoDTOConvert INSTANCE = Mappers.getMapper(SubjectInfoDTOConvert.class);

    SubjectInfoBO subjectInfoDTOTOSubjectInfoBO(SubjectInfoDTO subjectInfoDTO);

    SubjectInfoDTO subjectInfoBOTOSubjectInfoDTO(SubjectInfoBO subjectInfoBO);

    List<SubjectInfoDTO> convertBOToDTOList(List<SubjectInfoBO> subjectInfoBO);

}
