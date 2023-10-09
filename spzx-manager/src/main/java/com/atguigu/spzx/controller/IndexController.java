package com.atguigu.spzx.controller;

import com.atguigu.spzx.model.entity.system.SysUser;
import com.atguigu.spzx.service.SysUserService;
import com.atguigu.spzx.model.dto.system.LoginDto;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.system.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//後台登錄接口->通常單獨寫一個controller,方便攔截器放行
//@CrossOrigin(allowCredentials = "true", originPatterns = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/admin/system/index")
public class IndexController {
    @Autowired
    private SysUserService sysUserService;

    @PostMapping("/login")
    public Result<LoginVo> login(@RequestBody LoginDto loginDto){
        LoginVo  loginVo = sysUserService.login(loginDto);
        return Result.ok(loginVo);
    }

    @GetMapping("/getUserInfo")
    public Result<LoginVo> getUserInfo(@RequestHeader String token){
        SysUser sysUser = sysUserService.getUserInfo(token);
        return Result.ok();
    }
}
