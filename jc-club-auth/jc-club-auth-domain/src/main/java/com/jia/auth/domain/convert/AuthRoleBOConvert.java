package com.jia.auth.domain.convert;


import com.jia.auth.domain.entity.AuthRoleBO;
import com.jia.auth.infra.basic.entity.AuthRole;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthRoleBOConvert {

    AuthRoleBOConvert INSTANCE = Mappers.getMapper(AuthRoleBOConvert.class);

    AuthRole authRoleBOTOAuthRole(AuthRoleBO authRoleBO);
}
