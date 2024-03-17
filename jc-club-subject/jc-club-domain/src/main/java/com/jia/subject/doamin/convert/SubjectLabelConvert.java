package com.jia.subject.doamin.convert;

import com.jia.subject.doamin.entity.SubjectLabelBO;
import com.jia.subject.infra.basic.entity.SubjectLabel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectLabelConvert {

    SubjectLabelConvert INSTANCE = Mappers.getMapper(SubjectLabelConvert.class);
    SubjectLabel SubjectLabelBOTOSubjectLabel(SubjectLabelBO subjectLabelBO);

    List<SubjectLabelBO> SubjectLabelBOListTOSubjectLabelList(List<SubjectLabel> subjectLabelList);
}
