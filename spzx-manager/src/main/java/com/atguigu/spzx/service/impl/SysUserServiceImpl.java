package com.atguigu.spzx.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.atguigu.spzx.common.exception.SpzxException;
import com.atguigu.spzx.mapper.SysUserMapper;
import com.atguigu.spzx.model.dto.system.LoginDto;
import com.atguigu.spzx.model.entity.system.SysUser;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.model.vo.system.LoginVo;
import com.atguigu.spzx.model.vo.system.ValidateCodeVo;
import com.atguigu.spzx.service.SysUserService;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.concurrent.TimeUnit;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    SysUserMapper sysUserMapper;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;


    @Override
    public LoginVo login(LoginDto loginDto) {
        //CAPTCHA VERIFICATION
        String codeKeyUserInput = loginDto.getCaptcha();
        String verfCodeKeyInRedis = loginDto.getCodeKey();

        String codeActualValue = redisTemplate.opsForValue()
                .get("user:login:captcha:" + verfCodeKeyInRedis);
        if (!codeKeyUserInput.equalsIgnoreCase(codeActualValue)|| StrUtil.isEmpty(codeKeyUserInput)) {
            throw new SpzxException(ResultCodeEnum.CAPTCHA_ERROR);
        }
        redisTemplate.delete("user:login:captcha:"+verfCodeKeyInRedis);
        //VERIFICATION COMPLETE, RECORD REMOVED IN REDIS

        String userName = loginDto.getUserName();
        String password = loginDto.getPassword();

        //1.FIND THE USER BY NAME
        SysUser user = sysUserMapper.selectByUsername(userName);
        if (user==null){
           throw new SpzxException(ResultCodeEnum.LOGIN_ERROR);
        }

        //2.COMPARE PASSWORD IF USER FOUND
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!user.getPassword().equals(md5Password)){
            throw new SpzxException(ResultCodeEnum.LOGIN_ERROR);
        }

        //3.GENERATE AN UNIQUE TOKEN => FOR REDIS
        String token = IdUtil.simpleUUID();
        redisTemplate.opsForValue().set(
                "user:login:"+token,
                JSON.toJSONString(user),
                30,
                TimeUnit.MINUTES
                );

        //4.CONSTRUCT RESULT RETURN OBJ: loginVo
        LoginVo loginVo = new LoginVo();
        loginVo.setToken(token);
        loginVo.setRefresh_token("");

        return loginVo;
    }

    @Override
    public SysUser getUserInfo(String token) {
        //get JSON string from redis
        String s = redisTemplate.opsForValue().get(token);
        // parse json back to object then return
        return JSON.parseObject(s, SysUser.class);
    }

    @Override
    public Result<T> logout(String token) {
        Boolean delete = redisTemplate.delete("user:login:");
        if (delete)
            return Result.ok();
        return null;
    }

    @Override
    public ValidateCodeVo getCaptcha() {
        //generate code
        ShearCaptcha shearCaptcha = CaptchaUtil.createShearCaptcha(150, 48, 4, 6);
        String codeValue = shearCaptcha.getCode();
        String imageBase64 = shearCaptcha.getImageBase64().toString();


        //generate uuid as key
        String codeKey = IdUtil.simpleUUID();

        //store the captcha image to redis
        redisTemplate.opsForValue().set("user:login:captcha:"+
                codeKey,codeValue,5,TimeUnit.MINUTES);

        //construct response data
        ValidateCodeVo validateCodeVo = new ValidateCodeVo();
        validateCodeVo.setCodeKey(codeKey);
        validateCodeVo.setCodeValue(imageBase64);

        return validateCodeVo;
    }


}
