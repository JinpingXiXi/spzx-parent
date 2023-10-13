package com.atguigu.spzx.controller;


import com.atguigu.spzx.model.entity.system.SysMenu;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.service.SysMenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 菜单表 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2023-10-13
 */
@Tag(name = "菜单接口")
@RestController
@RequestMapping("/admin/system/sysMenu")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    @GetMapping("/findTreeList")
    public Result<List<SysMenu>> findTreeList(){
        List<SysMenu> resultList = sysMenuService.findTreeList();
        return Result.ok(resultList);
    }

    @PostMapping("/add")
    public Result<T> add(@RequestBody SysMenu sysMenu){
        sysMenuService.add(sysMenu);
        return Result.ok();
    }

    @PutMapping("/update")
    public Result<T> update(@RequestBody SysMenu sysMenu){
        sysMenuService.update(sysMenu);
        return Result.ok();
    }

    @DeleteMapping("/delete/{id}")
    public Result<T> delete(@PathVariable Long id){
        sysMenuService.delete(id);
        return Result.ok();
    }

    @Operation(summary = "查詢分配的菜單列表")
    @GetMapping("/findAssignMenuList/{roleId}")
    public Result<Map<String,Object>> findAssignMenuList(@PathVariable Long roleId){
        Map<String,Object> map = sysMenuService.findAssignMenuList(roleId);
        return Result.ok(map);
    }

}

