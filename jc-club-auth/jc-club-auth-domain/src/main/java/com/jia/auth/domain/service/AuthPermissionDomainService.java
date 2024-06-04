package com.jia.auth.domain.service;

import com.jia.auth.domain.entity.AuthPermissionBO;

import java.util.List;

public interface AuthPermissionDomainService {

    Boolean add (AuthPermissionBO authPermissionBO);

    Boolean update(AuthPermissionBO authPermissionBO);

    Boolean delete(AuthPermissionBO authPermissionBO);

    List<String> getPermission(String username);
}
