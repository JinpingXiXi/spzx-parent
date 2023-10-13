package com.atguigu.spzx.service.impl;

import com.atguigu.spzx.mapper.SysRoleMapper;
import com.atguigu.spzx.model.dto.system.SysRoleDto;
import com.atguigu.spzx.model.entity.system.SysRole;
import com.atguigu.spzx.service.SysRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2023-10-10
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public PageInfo<SysRole> findByPage(int pageNum, int pageSize, SysRoleDto sysRoleDto) {
        //在查询之前，开启分页
        PageHelper.startPage(pageNum, pageSize);
        //执行查询
        List<SysRole> list = sysRoleMapper.selectByPage(sysRoleDto);
        //将查询结果封装到PageInfo分页对象中
        return new PageInfo<>(list);
    }

    @Override
    public void add(SysRole sysRole) {
        sysRoleMapper.add(sysRole);
    }

    @Override
    public Integer deleteById(Long id) {
        return sysRoleMapper.deleteById(id);
    }

    @Override
    public void updateSysRole(SysRole sysRole) {
        sysRoleMapper.updateSysRole(sysRole);
    }

    @Override
    public List<SysRole> findAll() {
        return sysRoleMapper.findAll();
    }


}
