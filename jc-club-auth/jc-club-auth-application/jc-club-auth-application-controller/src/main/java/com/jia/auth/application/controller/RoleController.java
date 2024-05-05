package com.jia.auth.application.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.google.common.base.Preconditions;
import com.jia.auth.application.convert.AuthRoleDTOConverter;
import com.jia.auth.application.dto.AuthRoleDTO;
import com.jia.auth.common.entity.Result;
import com.jia.auth.domain.entity.AuthRoleBO;

import com.jia.auth.domain.service.AuthRoleDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/role")
@Slf4j
public class RoleController {

    @Resource
    private AuthRoleDomainService authRoleDomainService;


    //新增
    @RequestMapping("/add")
    public Result<Boolean> add(@RequestBody AuthRoleDTO authRoleDTO){
        try {
            if(log.isInfoEnabled()){
                log.info("RoleController.add.dto:{}", JSON.toJSONString(authRoleDTO));
            }
            //判断
            Preconditions.checkArgument(!StringUtils.isBlank(authRoleDTO.getRoleKey()), "角色key不能为空");
            Preconditions.checkArgument(!StringUtils.isBlank(authRoleDTO.getRoleName()), "角色名称不能为空");
            //转换
            AuthRoleBO authRoleBO = AuthRoleDTOConverter.INSTANCE.authRoleDTOTOBO(authRoleDTO);
            //调用服务
            return Result.ok(authRoleDomainService.add(authRoleBO));
        }catch (Exception e){
            log.info("UserController.register.error:{}",e.getMessage(),e);
            return Result.fail("新增角色失败");
        }
    }

    //更新
    @RequestMapping("/update")
    public Result<Boolean> update(@RequestBody AuthRoleDTO authRoleDTO){
        try {
            if(log.isInfoEnabled()){
                log.info("RoleController.update.dto:{}", JSON.toJSONString(authRoleDTO));
            }
            //判断
            Preconditions.checkArgument(!StringUtils.checkValNull(authRoleDTO.getId()), "角色key不能为空");
            //转换
            AuthRoleBO authRoleBO = AuthRoleDTOConverter.INSTANCE.authRoleDTOTOBO(authRoleDTO);
            //调用服务
            return Result.ok(authRoleDomainService.update(authRoleBO));
        }catch (Exception e){
            log.info("UserController.register.error:{}",e.getMessage(),e);
            return Result.fail("更新角色失败");
        }
    }

    //删除
    @RequestMapping("/delete")
    public Result<Boolean> delete(@RequestBody AuthRoleDTO authRoleDTO){
        try {
            if(log.isInfoEnabled()){
                log.info("RoleController.delete.dto:{}", JSON.toJSONString(authRoleDTO));
            }
            //判断
            Preconditions.checkArgument(!StringUtils.checkValNull(authRoleDTO.getId()), "角色key不能为空");
            //转换
            AuthRoleBO authRoleBO = AuthRoleDTOConverter.INSTANCE.authRoleDTOTOBO(authRoleDTO);
            //调用服务
            return Result.ok(authRoleDomainService.delete(authRoleBO));
        }catch (Exception e){
            log.info("UserController.register.error:{}",e.getMessage(),e);
            return Result.fail("删除角色失败");
        }
    }
}
