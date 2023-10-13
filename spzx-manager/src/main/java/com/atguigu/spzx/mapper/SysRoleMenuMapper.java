package com.atguigu.spzx.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysRoleMenuMapper {
    List<Long> selectAssignMenuList(long roleId);
}
