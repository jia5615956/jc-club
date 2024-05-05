package com.jia.auth.domain.service.impl;

import com.jia.auth.common.enums.IsDeletedFlagEnum;
import com.jia.auth.domain.entity.AuthRolePermissionBO;
import com.jia.auth.domain.service.AuthRolePermissionDomainService;
import com.jia.auth.infra.basic.entity.AuthRolePermission;
import com.jia.auth.infra.basic.service.AuthRolePermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@Service
@Slf4j
public class AuthRolePermissionDomainServiceImpl implements AuthRolePermissionDomainService {

    @Resource
    private AuthRolePermissionService authRolePermissionService;

    @Override
    public Boolean add(AuthRolePermissionBO rolePermissionBO) {
        List<AuthRolePermission> rolePermissionList = new LinkedList<>();
        //获取角色id
        Long roleId = rolePermissionBO.getRoleId();
        //循环
        rolePermissionBO.getPermissionIdList().forEach(permissionId -> {
            AuthRolePermission authRolePermission = new AuthRolePermission();
            authRolePermission.setRoleId(roleId);
            authRolePermission.setPermissionId(permissionId);
            authRolePermission.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
            rolePermissionList.add(authRolePermission);
        });
        //调用服务批量插入
        int count = authRolePermissionService.batchInsert(rolePermissionList);
        return count > 0;
    }
}
