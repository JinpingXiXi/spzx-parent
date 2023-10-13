package com.atguigu.spzx.mapper;

import com.atguigu.spzx.model.entity.system.SysMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author atguigu
 * @since 2023-10-13
 */
@Mapper
public interface SysMenuMapper {

    List<SysMenu> selectAll();

    void insert(SysMenu sysMenu);

    void update(SysMenu sysMenu);

    void delete(Long id);

    int findAllSubMenusById();
}
