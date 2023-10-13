package com.atguigu.spzx.service;

import com.atguigu.spzx.model.dto.system.AssignRoleDto;

/**
 * <p>
 * 用户角色 服务类
 * </p>
 *
 * @author atguigu
 * @since 2023-10-12
 */
public interface SysUserRoleService {

    void assignRole(AssignRoleDto assignRoleDto);
}
