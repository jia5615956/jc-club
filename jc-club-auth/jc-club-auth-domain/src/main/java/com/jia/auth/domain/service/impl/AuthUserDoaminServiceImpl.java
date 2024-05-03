package com.jia.auth.domain.service.impl;

import com.jia.auth.common.enums.AuthUserStatusEnum;
import com.jia.auth.common.enums.IsDeletedFlagEnum;
import com.jia.auth.domain.convert.AuthUserBOConvert;
import com.jia.auth.domain.entity.AuthUserBO;
import com.jia.auth.domain.service.AuthUserDoaminService;
import com.jia.auth.infra.basic.entity.AuthUser;
import com.jia.auth.infra.basic.service.AuthUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service
@Slf4j
public class AuthUserDoaminServiceImpl implements AuthUserDoaminService {

    @Resource
    private AuthUserService authUserService;

    //注册
    @Override
    public Boolean register(AuthUserBO authUserBO) {
        //转换
        AuthUser authUser = AuthUserBOConvert.INSTANCE.convertBOToEntity(authUserBO);
        //新增时，默认是状态是启用和未删除
        authUser.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        authUser.setStatus(AuthUserStatusEnum.OPEN.getCode());
        Integer insertFlag = authUserService.insert(authUser);
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
