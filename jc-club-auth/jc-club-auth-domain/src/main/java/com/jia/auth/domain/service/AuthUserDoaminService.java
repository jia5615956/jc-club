package com.jia.auth.domain.service;

import com.jia.auth.domain.entity.AuthUserBO;

public interface AuthUserDoaminService {

    public Boolean register(AuthUserBO authUserBO);

    Boolean update(AuthUserBO authUserBO);

    Boolean delete(AuthUserBO authUserBO);

}
