package com.jia.auth.domain.service;

import cn.dev33.satoken.stp.SaTokenInfo;
import com.jia.auth.domain.entity.AuthUserBO;

public interface AuthUserDoaminService {

    public Boolean register(AuthUserBO authUserBO);

    Boolean update(AuthUserBO authUserBO);

    Boolean delete(AuthUserBO authUserBO);

    SaTokenInfo doLogin(String validCode);

    public AuthUserBO getUserInfo(AuthUserBO authUserBO);
}
