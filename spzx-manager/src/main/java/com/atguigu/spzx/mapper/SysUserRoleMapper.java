package com.atguigu.spzx.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysUserRoleMapper {



    static void doAssign(@Param("userId")Long userId,
                         @Param("roleId") Long roleId) {
    }

    void deleteByUserId(Long userId);

}
