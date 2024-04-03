package com.jia.subject.doamin.convert;


import com.jia.subject.doamin.entity.SubjectCategoryBO;
import com.jia.subject.doamin.entity.SubjectInfoBO;
import com.jia.subject.infra.basic.entity.SubjectCategory;
import com.jia.subject.infra.basic.entity.SubjectInfo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectInfoConvert {

    SubjectInfoConvert INSTANCE = Mappers.getMapper(SubjectInfoConvert.class);

    SubjectInfo convertBoToCategory(SubjectInfoBO subjectInfoBO);




}
