package com.atguigu.spzx.mapper;

import com.atguigu.spzx.model.dto.system.SysRoleDto;
import com.atguigu.spzx.model.entity.system.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 角色 Mapper 接口
 * </p>
 *
 * @author atguigu
 * @since 2023-10-10
 */
@Mapper
public interface SysRoleMapper {

    List<SysRole> selectByPage(SysRoleDto sysRoleDto);

    void add(SysRole sysRole);

    Integer deleteById(Long id);

    void updateSysRole(SysRole sysRole);
}
