package com.jia.auth.domain.service.impl;

import com.jia.auth.common.enums.AuthUserStatusEnum;
import com.jia.auth.common.enums.IsDeletedFlagEnum;
import com.jia.auth.domain.convert.AuthPermissionBOConvert;
import com.jia.auth.domain.entity.AuthPermissionBO;
import com.jia.auth.domain.service.AuthPermissionDomainService;
import com.jia.auth.infra.basic.entity.AuthPermission;
import com.jia.auth.infra.basic.service.AuthPermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AuthPermissionDomainServiceImpl implements AuthPermissionDomainService {

    @Resource
    private AuthPermissionService authPermissionService;

    @Override
    public Boolean add(AuthPermissionBO authPermissionBO) {
        //转换
        AuthPermission authPermission = AuthPermissionBOConvert.INSTANCE.authPermissionBOTOAuthPermission(authPermissionBO);
        authPermission.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        authPermission.setStatus(AuthUserStatusEnum.OPEN.getCode());
        authPermission.setShow(0);
        Integer insert = authPermissionService.insert(authPermission);
        return insert > 0;
    }

    @Override
    public Boolean update(AuthPermissionBO authPermissionBO) {
        AuthPermission authPermission = AuthPermissionBOConvert.INSTANCE.authPermissionBOTOAuthPermission(authPermissionBO);
        Integer update = authPermissionService.update(authPermission);
        return update > 0;
    }

    @Override
    public Boolean delete(AuthPermissionBO authPermissionBO) {
        //转换
        AuthPermission authPermission = AuthPermissionBOConvert.INSTANCE.authPermissionBOTOAuthPermission(authPermissionBO);
        authPermission.setIsDeleted(IsDeletedFlagEnum.DELETED.getCode());
        Integer update = authPermissionService.update(authPermission);
        return update > 0;
    }
}
