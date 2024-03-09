package com.jia.subject.application.convert;


import com.jia.subject.application.dto.SubjectCategoryDTO;
import com.jia.subject.doamin.entity.SubjectCategoryBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubjectCategoryDTOConverter {

    SubjectCategoryDTOConverter INSTANCE = Mappers.getMapper(SubjectCategoryDTOConverter.class);

    SubjectCategoryBO convertDTOToCategoryBO(SubjectCategoryDTO subjectCategoryDTO);
}
