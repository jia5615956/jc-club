package com.jia.auth.infra.basic.service;


import com.jia.auth.infra.basic.entity.AuthRole;


/**
 * (AuthRole)表服务接口
 *
 * @author makejava
 * @since 2024-05-05 11:25:48
 */
public interface AuthRoleService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AuthRole queryById(Long id);



    /**
     * 新增数据
     *
     * @param authRole 实例对象
     * @return 实例对象
     */
    Integer insert(AuthRole authRole);

    /**
     * 修改数据
     *
     * @param authRole 实例对象
     * @return 实例对象
     */
    Integer update(AuthRole authRole);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    AuthRole queryByCondition(AuthRole authRole);
}
