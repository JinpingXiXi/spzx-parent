package com.atguigu.spzx.controller;


import com.atguigu.spzx.model.dto.system.AssignRoleDto;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.service.SysUserRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户角色 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2023-10-12
 */
@Tag(name="用户角色接口")
@RestController
@RequestMapping("/admin/system/sysUserRole")
public class SysUserRoleController {

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Operation(summary = "分配角色")
    @PostMapping("/assignRole")
    public Result<T> assignRole(@RequestBody AssignRoleDto assignRoleDto){
        sysUserRoleService.assignRole(assignRoleDto);
        return Result.ok();
    }
}

