package com.jia.auth.domain.service.impl;

import com.jia.auth.common.enums.IsDeletedFlagEnum;
import com.jia.auth.domain.convert.AuthRoleBOConvert;
import com.jia.auth.domain.entity.AuthRoleBO;
import com.jia.auth.domain.service.AuthRoleDomainService;
import com.jia.auth.infra.basic.entity.AuthRole;
import com.jia.auth.infra.basic.service.AuthRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class AuthRoleDomainServiceImpl implements AuthRoleDomainService {

    @Resource
    private AuthRoleService authRoleService;

    @Override
    public Boolean add(AuthRoleBO authRoleBO) {
        //转换
        AuthRole authRole = AuthRoleBOConvert.INSTANCE.authRoleBOTOAuthRole(authRoleBO);
        authRole.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        //调用服务
        Integer insert = authRoleService.insert(authRole);
        return insert > 0;
    }

    @Override
    public Boolean update(AuthRoleBO authRoleBO) {
        //转换
        AuthRole authRole = AuthRoleBOConvert.INSTANCE.authRoleBOTOAuthRole(authRoleBO);
        Integer update = authRoleService.update(authRole);
        return update > 0;
    }

    @Override
    public Boolean delete(AuthRoleBO authRoleBO) {
        //转换
        AuthRole authRole = AuthRoleBOConvert.INSTANCE.authRoleBOTOAuthRole(authRoleBO);
        authRole.setIsDeleted(IsDeletedFlagEnum.DELETED.getCode());
        Integer update = authRoleService.update(authRole);
        return update > 0;
    }
}
