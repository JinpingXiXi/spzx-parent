package com.atguigu.spzx.controller;

import com.atguigu.spzx.model.dto.system.SysUserDto;
import com.atguigu.spzx.model.entity.system.SysUser;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.service.SysUserService;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2023-10-10
 */
@Tag(name = "用户接口")
@RestController
@RequestMapping("/admin/system/sysUser")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @Operation(summary = "分页查询")
    @PostMapping("/findByPage/{pageNum}/{pageSize}")
    public Result<PageInfo<SysUser>> findByPage(@PathVariable int pageNum, @PathVariable int pageSize,
                                @RequestBody SysUserDto sysUserDto){
        PageInfo<SysUser> pageInfo = sysUserService.findByPage(pageNum, pageSize, sysUserDto);
        return Result.ok(pageInfo);
    }

    @Operation(summary = "添加")
    @PostMapping("/add")
    public Result<T> add(@RequestBody SysUser sysUser){
        sysUserService.add(sysUser);
        return Result.ok();
    }

    @Operation(summary = "修改")
    @PutMapping("/update")
    public Result<T> update(@RequestBody SysUser sysUser){
        sysUserService.update(sysUser);
        return Result.ok();
    }

    @Operation(summary = "逻辑删除")
    @DeleteMapping("/delete/{id}")
    public Result<T> delete(@PathVariable long id){
        sysUserService.delete(id);
        return Result.ok();
    }
}
