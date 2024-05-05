package com.jia.auth.application.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.google.common.base.Preconditions;
import com.jia.auth.application.convert.AuthPermissionDTOConverter;
import com.jia.auth.application.dto.AuthPermissionDTO;
import com.jia.auth.common.entity.Result;
import com.jia.auth.domain.entity.AuthPermissionBO;
import com.jia.auth.domain.service.AuthPermissionDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/permission")
@Slf4j
public class PermissionController {

    @Resource
    private AuthPermissionDomainService authPermissionDomainService;

    //新增
    @RequestMapping("/add")
    public Result<Boolean> add(@RequestBody AuthPermissionDTO authPermissionDTO){
        try {
            if(log.isInfoEnabled()){
                log.info("PermissionController.add.dto:{}", JSON.toJSONString(authPermissionDTO));
            }
            //判断
            Preconditions.checkArgument(!StringUtils.isBlank(authPermissionDTO.getName()), "权限名称不能为空");
            Preconditions.checkNotNull(authPermissionDTO.getParentId(), "权限父id不能为空");
            //转换
            AuthPermissionBO authPermissionBO = AuthPermissionDTOConverter.INSTANCE.authPermissionDTOTOBO(authPermissionDTO);
            //调用服务
            return Result.ok(authPermissionDomainService.add(authPermissionBO));
        }catch (Exception e){
            log.info("UserController.register.error:{}",e.getMessage(),e);
            return Result.fail("新增权限失败");
        }
    }

    //更新
    @RequestMapping("/update")
    public Result<Boolean> update(@RequestBody AuthPermissionDTO authPermissionDTO){
        try {
            if(log.isInfoEnabled()){
                log.info("PermissionController.update.dto:{}", JSON.toJSONString(authPermissionDTO));
            }
            //判断
            Preconditions.checkArgument(!StringUtils.checkValNull(authPermissionDTO.getId()), "角色key不能为空");
            //转换
            AuthPermissionBO authPermissionBO = AuthPermissionDTOConverter.INSTANCE.authPermissionDTOTOBO(authPermissionDTO);
            //调用服务
            return Result.ok(authPermissionDomainService.update(authPermissionBO));
        }catch (Exception e){
            log.info("UserController.register.error:{}",e.getMessage(),e);
            return Result.fail("更新权限失败");
        }
    }

    //删除
    @RequestMapping("/delete")
    public Result<Boolean> delete(@RequestBody AuthPermissionDTO authPermissionDTO){
        try {
            if(log.isInfoEnabled()){
                log.info("PermissionController.delete.dto:{}", JSON.toJSONString(authPermissionDTO));
            }
            //判断
            Preconditions.checkArgument(!StringUtils.checkValNull(authPermissionDTO.getId()), "角色key不能为空");
            //转换
            AuthPermissionBO authPermissionBO = AuthPermissionDTOConverter.INSTANCE.authPermissionDTOTOBO(authPermissionDTO);
            //调用服务
            return Result.ok(authPermissionDomainService.delete(authPermissionBO));
        }catch (Exception e){
            log.info("UserController.register.error:{}",e.getMessage(),e);
            return Result.fail("删除权限失败");
        }
    }
}
