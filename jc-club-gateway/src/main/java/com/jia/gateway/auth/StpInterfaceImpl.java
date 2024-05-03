package com.jia.gateway.auth;

import cn.dev33.satoken.stp.StpInterface;
import com.alibaba.nacos.common.utils.StringUtils;
import com.google.gson.Gson;
import com.jia.gateway.redis.RedisUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * 自定义权限验证接口扩展
 */
@Component
public class StpInterfaceImpl implements StpInterface {

    @Resource
    private RedisUtil redisUtil;

    private String authPermissionPrefix = "auth.permission";
    private String authRolePrefix = "auth.role";

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        return getAuth(loginId.toString(),authPermissionPrefix);
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        return getAuth(loginId.toString(),authRolePrefix);
    }

    private List<String> getAuth(String loginId,String prxifx){
        //创建key
        String buildKey = redisUtil.buildKey(prxifx, loginId.toString());
        //通过key获取
        String authValue = redisUtil.get(buildKey);
        //判断是否为空
        if (StringUtils.isBlank(authValue)) {
            //去数据库查找
            return Collections.emptyList();
        }

        return new Gson().fromJson(authValue, List.class);
    }

}

