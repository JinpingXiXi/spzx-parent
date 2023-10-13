package com.atguigu.spzx.service.impl;

import com.atguigu.spzx.mapper.SysUserRoleMapper;
import com.atguigu.spzx.model.dto.system.AssignRoleDto;
import com.atguigu.spzx.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 用户角色 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2023-10-12
 */
@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Transactional //开启事务
    @Override
    public void assignRole(AssignRoleDto assignRoleDto) {
        //1.删除指定用户的所有角色信息
        sysUserRoleMapper.deleteByUserId(assignRoleDto.getUserId());

        //2.添加用户与角色的关联信息
        sysUserRoleMapper.insertBatch(assignRoleDto);
    }
}
