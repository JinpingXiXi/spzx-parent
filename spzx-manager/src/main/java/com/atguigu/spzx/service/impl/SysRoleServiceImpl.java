package com.atguigu.spzx.service.impl;

import com.atguigu.spzx.mapper.SysRoleMapper;
import com.atguigu.spzx.mapper.SysUserRoleMapper;
import com.atguigu.spzx.model.dto.system.SysRoleDto;
import com.atguigu.spzx.model.entity.system.SysRole;
import com.atguigu.spzx.service.SysRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

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
        sysRoleMapper.insert(sysRole);
    }

    @Override
    public void update(SysRole sysRole) {
        sysRoleMapper.update(sysRole);
    }

    @Override
    public void delete(long id) {
        sysRoleMapper.deleteById(id);
    }

    @Override
    public Map<String, Object> findAssignRoleList(long userId) {
        //1.查询所有角色 > sys_role表
        List<SysRole> allRoleList = sysRoleMapper.selectAll();
        //2.查询指定用户拥有的角色id > sys_user_role表
        List<Long> assignRoleList = sysUserRoleMapper.selectByUserId(userId);

        //封装到Map中
        Map<String, Object> map = new HashMap<>();
        map.put("allRoleList", allRoleList);
        map.put("assignRoleList", assignRoleList);
        return map;
    }
}
