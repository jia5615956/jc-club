package com.jia.auth.application.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.google.common.base.Preconditions;
import com.jia.auth.application.convert.AuthUserDTOConverter;
import com.jia.auth.application.dto.AuthUserDTO;
import com.jia.auth.domain.entity.AuthUserBO;
import com.jia.auth.common.entity.Result;

import com.jia.auth.domain.service.AuthUserDoaminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.annotation.Resource;


@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Resource
    private AuthUserDoaminService authUserDoaminService;

    //注册
    @PostMapping("/register")
    public Result<Boolean> register(@RequestBody AuthUserDTO authUserDTO){
        try {
            if(log.isInfoEnabled()){
                log.info("UserController.register.dto:{}", JSON.toJSONString(authUserDTO));
            }
            //判断
            Preconditions.checkArgument(!StringUtils.isBlank(authUserDTO.getUserName()), "用户名不能为空");
            //转换
            AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertAuthUserDTOTOAuthUserBO(authUserDTO);
            //调用服务
            Boolean registerFlag = authUserDoaminService.register(authUserBO);
            return Result.ok(registerFlag);
        }catch (Exception e){
            log.info("UserController.register.error:{}",e.getMessage(),e);
            return Result.fail("注册用户失败");
        }
    }

    //修改用户信息
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody AuthUserDTO authUserDTO){
        try {
            if(log.isInfoEnabled()){
                log.info("UserController.update.dto:{}", JSON.toJSONString(authUserDTO));
            }
            //校验
            Preconditions.checkArgument(!StringUtils.isBlank(authUserDTO.getUserName()), "用户名不能为空");
            //转换
            AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertAuthUserDTOTOAuthUserBO(authUserDTO);
            //调用服务
            return Result.ok(authUserDoaminService.update(authUserBO));
        }catch (Exception e){
            log.info("UserController.update.error:{}",e.getMessage(),e);
            return Result.fail("更新用户失败");
        }
    }

    //删除用户信息
    @PostMapping("/delete")
    public Result<Boolean> delete(@RequestBody AuthUserDTO authUserDTO){
        try {
            if(log.isInfoEnabled()){
                log.info("UserController.delete.dto:{}", JSON.toJSONString(authUserDTO));
            }
            //转换
            AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertAuthUserDTOTOAuthUserBO(authUserDTO);
            //调用服务
            return Result.ok(authUserDoaminService.delete(authUserBO));
        }catch (Exception e){
            log.info("UserController.delete.error:{}",e.getMessage(),e);
            return Result.fail("删除用户失败");
        }
    }

    //用户启用/禁用
    @PostMapping("/changeStatus")
    public Result<Boolean> changeStatus(@RequestBody AuthUserDTO authUserDTO){
        try {
            if(log.isInfoEnabled()){
                log.info("UserController.changeStatus.dto:{}", JSON.toJSONString(authUserDTO));
            }
            //转换
            AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertAuthUserDTOTOAuthUserBO(authUserDTO);
            //调用服务
            return Result.ok(authUserDoaminService.update(authUserBO));
        }catch (Exception e){
            log.info("UserController.changeStatus.error:{}",e.getMessage(),e);
            return Result.fail("状态更新失败");
        }
    }

    // 测试登录，浏览器访问： http://localhost:8081/user/doLogin?username=zhang&password=123456
    @RequestMapping("doLogin")
    public String doLogin(String username, String password) {
        // 此处仅作模拟示例，真实项目需要从数据库中查询数据进行比对
        if("zhang".equals(username) && "123456".equals(password)) {         
            StpUtil.login(10001);
            return "登录成功";
        }
        return "登录失败";
    }

    // 查询登录状态，浏览器访问： http://localhost:8081/user/isLogin
    @RequestMapping("isLogin")
    public String isLogin() {
        return "当前会话是否登录：" + StpUtil.isLogin();
    }



}

