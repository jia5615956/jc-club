package com.jia.subject.application.convert;

import com.jia.subject.application.dto.SubjectLabelDTO;
import com.jia.subject.doamin.entity.SubjectLabelBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectLabelDTOConvert {

    SubjectLabelDTOConvert INSTANCE = Mappers.getMapper(SubjectLabelDTOConvert.class);

    SubjectLabelBO subjectLabelDTOTOSubjectLabelBO(SubjectLabelDTO subjectLabelDTO);

    List<SubjectLabelDTO> SubjectLabelBOListTOSubjectLabelDTO(List<SubjectLabelBO> subjectLabelBOList);
}
