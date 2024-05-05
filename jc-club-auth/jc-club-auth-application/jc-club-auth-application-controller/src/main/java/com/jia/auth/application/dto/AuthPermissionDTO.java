package com.jia.auth.application.dto;


import lombok.Data;

@Data
public class AuthPermissionDTO {
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
