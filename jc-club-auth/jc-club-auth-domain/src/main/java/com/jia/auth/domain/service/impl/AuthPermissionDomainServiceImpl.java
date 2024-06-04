package com.jia.auth.domain.service.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jia.auth.common.enums.AuthUserStatusEnum;
import com.jia.auth.common.enums.IsDeletedFlagEnum;
import com.jia.auth.domain.convert.AuthPermissionBOConvert;
import com.jia.auth.domain.entity.AuthPermissionBO;
import com.jia.auth.domain.redis.RedisUtil;
import com.jia.auth.domain.service.AuthPermissionDomainService;
import com.jia.auth.infra.basic.entity.AuthPermission;
import com.jia.auth.infra.basic.service.AuthPermissionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthPermissionDomainServiceImpl implements AuthPermissionDomainService {

    @Resource
    private AuthPermissionService authPermissionService;

    @Resource
    private RedisUtil redisUtil;
    private String authPermissionPrefix = "auth.permission";

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

    //查询用户权限
    @Override
    public List<String> getPermission(String username) {
        //从redis中获取
        //构建key
        String permissionKey = redisUtil.buildKey(authPermissionPrefix, username);
        //获取值
        String permissionValue = redisUtil.get(permissionKey);
        //判断是否为空
        if(StringUtils.isBlank(permissionValue)){
            return Collections.emptyList();
        }
        List<AuthPermission> permissionList = new Gson().fromJson(permissionValue,new TypeToken<List<AuthPermission>>() {
        }.getType());
        List<String> authList = permissionList.stream().map(AuthPermission::getPermissionKey).collect(Collectors.toList());
        return authList;
    }
}
