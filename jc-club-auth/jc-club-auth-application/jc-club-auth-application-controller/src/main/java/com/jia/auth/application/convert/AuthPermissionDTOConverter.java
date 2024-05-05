package com.jia.auth.application.convert;


import com.jia.auth.application.dto.AuthPermissionDTO;
import com.jia.auth.application.dto.AuthRoleDTO;
import com.jia.auth.domain.entity.AuthPermissionBO;
import com.jia.auth.domain.entity.AuthRoleBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthPermissionDTOConverter {

    AuthPermissionDTOConverter INSTANCE = Mappers.getMapper(AuthPermissionDTOConverter.class);

    AuthPermissionBO authPermissionDTOTOBO(AuthPermissionDTO authPermissionDTO);
}
