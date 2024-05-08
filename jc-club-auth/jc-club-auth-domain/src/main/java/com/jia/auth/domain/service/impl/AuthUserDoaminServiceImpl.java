package com.jia.auth.domain.service.impl;

import com.google.gson.Gson;
import com.jia.auth.common.enums.AuthUserStatusEnum;
import com.jia.auth.common.enums.IsDeletedFlagEnum;
import com.jia.auth.domain.constants.AuthConstant;
import com.jia.auth.domain.convert.AuthUserBOConvert;
import com.jia.auth.domain.entity.AuthUserBO;
import com.jia.auth.domain.redis.RedisUtil;
import com.jia.auth.domain.service.AuthUserDoaminService;
import com.jia.auth.infra.basic.entity.*;
import com.jia.auth.infra.basic.service.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AuthUserDoaminServiceImpl implements AuthUserDoaminService {

    @Resource
    private AuthUserService authUserService;
    @Resource
    private AuthRoleService authRoleService;
    @Resource
    private AuthUserRoleService authUserRoleService;
    @Resource
    private RedisUtil redisUtil;

    private String authPermissionPrefix = "auth.permission";

    private String authRolePrefix = "auth.role";
    @Autowired
    private AuthRolePermissionService authRolePermissionService;
    @Resource
    private AuthPermissionService authPermissionService;


    //注册
    @Override
    @SneakyThrows
    @Transactional(rollbackFor = Exception.class)
    public Boolean register(AuthUserBO authUserBO) {
        //转换
        AuthUser authUser = AuthUserBOConvert.INSTANCE.convertBOToEntity(authUserBO);
        //新增时，默认是状态是启用和未删除
        authUser.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        authUser.setStatus(AuthUserStatusEnum.OPEN.getCode());
        Integer insertFlag = authUserService.insert(authUser);
        //同时给客户角色,获取正常用户角色的id
        AuthRole authRole = new AuthRole();
        authRole.setRoleKey(AuthConstant.NORMAL_USER);
        AuthRole roleResult = authRoleService.queryByCondition(authRole);
        //获取角色id
        Long roleId = roleResult.getId();
        //获取新增用户id
        Long userId = authUser.getId();
        //将这两个id添加到表里
        AuthUserRole authUserRole = new AuthUserRole();
        authUserRole.setUserId(userId);
        authUserRole.setRoleId(roleId);
        authUserRole.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        authUserRoleService.insert(authUserRole);
        //同时将角色信息放入缓存中,创建缓存key
        String roleKey = redisUtil.buildKey(authRolePrefix, authUser.getUserName());
        List<AuthRole> roleList = new LinkedList<>();
        roleList.add(authRole);
        redisUtil.set(roleKey,new Gson().toJson(roleList));
        //将权限信息放入缓存中
        AuthRolePermission authRolePermission = new AuthRolePermission();
        authRolePermission.setRoleId(roleId);
        //在角色权限表中查角色对应的权限
        List<AuthRolePermission> authRolePermissionList = authRolePermissionService.queryByCondition(authRolePermission);
        //再根据权限id查询对应的权限信息
        List<Long> permissionIdList = authRolePermissionList.stream().map(AuthRolePermission::getPermissionId).collect(Collectors.toList());
        List<AuthPermission> permissionList = authPermissionService.queryByRoleList(permissionIdList);
        String permissionKey = redisUtil.buildKey(authPermissionPrefix, authUser.getUserName());
        redisUtil.set(permissionKey,new Gson().toJson(permissionList));
        return insertFlag > 0;
    }

    //更新用户信息
    @Override
    public Boolean update(AuthUserBO authUserBO) {
        //转换
        AuthUser authUser = AuthUserBOConvert.INSTANCE.convertBOToEntity(authUserBO);
        Integer update = authUserService.updateByUserName(authUser);
        return update > 0;
    }

    //删除用户
    @Override
    public Boolean delete(AuthUserBO authUserBO) {
        //转换
        AuthUser authUser = AuthUserBOConvert.INSTANCE.convertBOToEntity(authUserBO);
        authUser.setIsDeleted(IsDeletedFlagEnum.DELETED.getCode());
        Integer update = authUserService.update(authUser);
        return update > 0;
    }

}