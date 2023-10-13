package com.atguigu.spzx.mapper;

import com.atguigu.spzx.model.dto.system.AssignRoleDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 用户角色 Mapper 接口
 * </p>
 *
 * @author atguigu
 * @since 2023-10-12
 */
@Mapper
public interface SysUserRoleMapper {

    List<Long> selectByUserId(long userId);

    void deleteByUserId(Long userId);

    void insertBatch(AssignRoleDto assignRoleDto);
}
