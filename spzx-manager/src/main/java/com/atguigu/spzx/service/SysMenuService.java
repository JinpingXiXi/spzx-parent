package com.atguigu.spzx.service;

import com.atguigu.spzx.model.entity.system.SysMenu;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author atguigu
 * @since 2023-10-13
 */
public interface SysMenuService {

    List<SysMenu> findTreeList();

    void add(SysMenu sysMenu);

    void update(SysMenu sysMenu);
}
