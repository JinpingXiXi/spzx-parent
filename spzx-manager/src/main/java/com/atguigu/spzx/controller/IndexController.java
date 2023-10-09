package com.atguigu.spzx.controller;

import com.atguigu.spzx.model.dto.system.LoginDto;
import com.atguigu.spzx.model.entity.system.SysUser;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.system.LoginVo;
import com.atguigu.spzx.model.vo.system.ValidateCodeVo;
import com.atguigu.spzx.service.SysUserService;
import org.apache.poi.ss.formula.functions.T;
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

    @GetMapping("/logout")
    public Result<T> logout(@RequestHeader(value="token") String token){
        return sysUserService.logout(token);
    }

    @GetMapping("/getCaptcha")
    public Result<ValidateCodeVo> getCaptcha(){
        ValidateCodeVo validateCodeVo = sysUserService.getCaptcha();
        return Result.ok(validateCodeVo);
    }


}
