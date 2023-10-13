package com.atguigu.spzx.controller;


import com.atguigu.spzx.model.dto.system.SysRoleDto;
import com.atguigu.spzx.model.entity.system.SysRole;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.service.SysRoleService;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 角色 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2023-10-10
 */
@Tag(name = "角色接口")
@RestController
@RequestMapping("/admin/system/sysRole")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @Operation(summary = "分页查询")
    @PostMapping("/findByPage/{pageNum}/{pageSize}")
    public Result<PageInfo<SysRole>> findByPage(@PathVariable int pageNum,
                                                @PathVariable int pageSize,
                                                @RequestBody SysRoleDto sysRoleDto) {
        PageInfo<SysRole> pageInfo = sysRoleService.findByPage(pageNum, pageSize, sysRoleDto);
        return Result.ok(pageInfo);
    }

    @Operation(summary = "添加")
    @PostMapping("/add")
    public Result<T> add(@RequestBody SysRole sysRole) {
        sysRoleService.add(sysRole);
        return Result.ok();
    }

    @Operation(summary = "修改")
    @PutMapping("/update")
    public Result<T> update(@RequestBody SysRole sysRole) {
        sysRoleService.update(sysRole);
        return Result.ok();
    }

    @Operation(summary = "逻辑删除")
    @DeleteMapping("/delete/{id}")
    public Result<T> delete(@PathVariable long id) {
        sysRoleService.delete(id);
        return Result.ok();
    }

    @Operation(summary = "查询分配角色列表")
    @GetMapping("/findAssignRoleList/{userId}")
    public Result<Map<String,Object>> findAssignRoleList(@PathVariable long userId) {
        //包含所有角色列表，指定用户拥有的角色列表
        Map<String,Object> map = sysRoleService.findAssignRoleList(userId);
        return Result.ok(map);
    }
}

