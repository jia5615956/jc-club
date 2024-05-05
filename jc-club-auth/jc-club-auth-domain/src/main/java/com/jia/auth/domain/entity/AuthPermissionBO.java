package com.jia.auth.domain.entity;


import lombok.Data;

@Data
public class AuthPermissionBO {
    private Long id;

    private String name;

    private Long parentId;

    private Integer type;

    private String menuUrl;

    private Integer status;

    private Integer show;

    private String icon;

    private String permissionKey;

}
