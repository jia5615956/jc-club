package com.jia.subject.application.convert;

import com.jia.subject.application.dto.SubjectCategoryDTO;
import com.jia.subject.doamin.entity.SubjectCategoryBO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-11T19:00:29+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.7 (Oracle Corporation)"
)
public class SubjectCategoryDTOConverterImpl implements SubjectCategoryDTOConverter {

    @Override
    public SubjectCategoryBO convertDTOToCategoryBO(SubjectCategoryDTO subjectCategoryDTO) {
        if ( subjectCategoryDTO == null ) {
            return null;
        }

        SubjectCategoryBO subjectCategoryBO = new SubjectCategoryBO();

        subjectCategoryBO.setId( subjectCategoryDTO.getId() );
        subjectCategoryBO.setCategoryName( subjectCategoryDTO.getCategoryName() );
        subjectCategoryBO.setCategoryType( subjectCategoryDTO.getCategoryType() );
        subjectCategoryBO.setImageUrl( subjectCategoryDTO.getImageUrl() );
        subjectCategoryBO.setParentId( subjectCategoryDTO.getParentId() );

        return subjectCategoryBO;
    }

    @Override
    public List<SubjectCategoryDTO> convertBOListToDTOList(List<SubjectCategoryBO> subjectCategoryDTO) {
        if ( subjectCategoryDTO == null ) {
            return null;
        }

        List<SubjectCategoryDTO> list = new ArrayList<SubjectCategoryDTO>( subjectCategoryDTO.size() );
        for ( SubjectCategoryBO subjectCategoryBO : subjectCategoryDTO ) {
            list.add( subjectCategoryBOToSubjectCategoryDTO( subjectCategoryBO ) );
        }

        return list;
    }

    protected SubjectCategoryDTO subjectCategoryBOToSubjectCategoryDTO(SubjectCategoryBO subjectCategoryBO) {
        if ( subjectCategoryBO == null ) {
            return null;
        }

        SubjectCategoryDTO subjectCategoryDTO = new SubjectCategoryDTO();

        subjectCategoryDTO.setId( subjectCategoryBO.getId() );
        subjectCategoryDTO.setCategoryName( subjectCategoryBO.getCategoryName() );
        subjectCategoryDTO.setCategoryType( subjectCategoryBO.getCategoryType() );
        subjectCategoryDTO.setImageUrl( subjectCategoryBO.getImageUrl() );
        subjectCategoryDTO.setParentId( subjectCategoryBO.getParentId() );

        return subjectCategoryDTO;
    }
}
