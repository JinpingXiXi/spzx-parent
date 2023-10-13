package com.atguigu.spzx.service.impl;

import com.atguigu.spzx.mapper.SysUserRoleMapper;
import com.atguigu.spzx.model.dto.system.AssignRoleDto;
import com.atguigu.spzx.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Transactional
    @Override
    public void doAssign(AssignRoleDto assignRoleDto) {
        sysUserRoleMapper.deleteByUserId(assignRoleDto.getUserId());
        List<Long> roleIdList = assignRoleDto.getRoleIdList();
        roleIdList.forEach(roleId->{
            SysUserRoleMapper.doAssign(assignRoleDto.getUserId(),roleId);
        });

    }
}
