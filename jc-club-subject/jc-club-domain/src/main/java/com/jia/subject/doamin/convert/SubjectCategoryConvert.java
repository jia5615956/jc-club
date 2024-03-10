package com.jia.subject.doamin.convert;


import com.jia.subject.doamin.entity.SubjectCategoryBO;
import com.jia.subject.infra.basic.entity.SubjectCategory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectCategoryConvert {

    SubjectCategoryConvert INSTANCE = Mappers.getMapper(SubjectCategoryConvert.class);

    SubjectCategory convertBoToCategory(SubjectCategoryBO subjectCategoryBO);

    List<SubjectCategoryBO> convertListToCategoryBOList(List<SubjectCategory> subjectCategoryList);
}
