package com.jia.auth.domain.convert;


import com.jia.auth.domain.entity.AuthPermissionBO;
import com.jia.auth.domain.entity.AuthRoleBO;
import com.jia.auth.infra.basic.entity.AuthPermission;
import com.jia.auth.infra.basic.entity.AuthRole;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthPermissionBOConvert {

    AuthPermissionBOConvert INSTANCE = Mappers.getMapper(AuthPermissionBOConvert.class);

    AuthPermission authPermissionBOTOAuthPermission(AuthPermissionBO authPermissionBO);
}
