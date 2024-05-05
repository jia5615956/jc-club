package com.jia.auth.domain.service;

import com.jia.auth.domain.entity.AuthRoleBO;

public interface AuthRoleDomainService {
    Boolean add(AuthRoleBO authRoleBO);

    Boolean update(AuthRoleBO authRoleBO);

    Boolean delete(AuthRoleBO authRoleBO);
}
