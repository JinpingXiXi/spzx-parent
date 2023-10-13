package com.atguigu.spzx.service.impl;

import com.atguigu.spzx.mapper.SysMenuMapper;
import com.atguigu.spzx.model.entity.system.SysMenu;
import com.atguigu.spzx.service.SysMenuService;
import com.atguigu.spzx.utils.MenuUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2023-10-13
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenu> findTreeList() {
        //查询所有菜单
        List<SysMenu> allList = sysMenuMapper.selectAll();

        //转为树节点菜单
        return MenuUtil.toTreeList(allList);
    }

    @Override
    public void add(SysMenu sysMenu) {
        sysMenuMapper.insert(sysMenu);
    }

    @Override
    public void update(SysMenu sysMenu) {
        sysMenuMapper.update(sysMenu);
    }
}
