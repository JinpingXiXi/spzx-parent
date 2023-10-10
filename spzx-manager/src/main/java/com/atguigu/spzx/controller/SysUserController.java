package com.atguigu.spzx.controller;

import com.atguigu.spzx.model.dto.system.SysUserDto;
import com.atguigu.spzx.model.entity.system.SysUser;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.service.impl.SysUserServiceImpl;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/system/sysUser")
public class SysUserController {

    @Autowired
    private SysUserServiceImpl sysUserService;

    @PostMapping("/findByPage/{pageNum}/{pageSize}")
    public Result findByPage(@RequestBody SysUserDto sysUserDto,
                             @PathVariable Integer pageNum,
                             @PathVariable Integer pageSize){
        PageInfo<SysUser> pageInfo = sysUserService.findByPage(sysUserDto,pageNum,pageSize);
        return Result.ok(pageInfo);
    }
}
