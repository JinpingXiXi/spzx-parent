package com.atguigu.spzx.mapper;

import com.atguigu.spzx.model.dto.system.AssignMenuDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysRoleMenuMapper {
    List<Long> selectAssignMenuList(long roleId);

    void deleteByRoleId(Long roleId);

    void insertBatch(AssignMenuDto assignMenuDto);

}
