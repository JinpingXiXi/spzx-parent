package com.atguigu.spzx.service;

import com.atguigu.spzx.model.dto.system.SysRoleDto;
import com.atguigu.spzx.model.entity.system.SysRole;
import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * <p>
 * 角色 服务类
 * </p>
 *
 * @author atguigu
 * @since 2023-10-10
 */
public interface SysRoleService {

    PageInfo<SysRole> findByPage(int pageNum, int pageSize, SysRoleDto sysRoleDto);

    void add(SysRole sysRole);

    void update(SysRole sysRole);

    void delete(long id);

    Map<String, Object> findAssignRoleList(long userId);
}
