package com.atguigu.spzx.mapper;

import com.atguigu.spzx.model.dto.system.SysUserDto;
import com.atguigu.spzx.model.entity.system.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysUserMapper {
    SysUser selectByUserName(String userName);

    List<SysUser> selectByPage(SysUserDto sysUserDto);

    void insert(SysUser sysUser);

    void update(SysUser sysUser);

    void deleteById(long id);
}
