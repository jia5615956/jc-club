package com.jia.auth.application.convert;


import com.jia.auth.application.dto.AuthRoleDTO;
import com.jia.auth.domain.entity.AuthRoleBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthRoleDTOConverter {

    AuthRoleDTOConverter INSTANCE = Mappers.getMapper(AuthRoleDTOConverter.class);

    AuthRoleBO authRoleDTOTOBO(AuthRoleDTO authRoleDTO);
}
