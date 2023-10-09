package com.atguigu.spzx.service.impl;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.atguigu.spzx.common.exception.SpzxException;
import com.atguigu.spzx.mapper.SysUserMapper;
import com.atguigu.spzx.model.dto.system.LoginDto;
import com.atguigu.spzx.model.entity.system.SysUser;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.model.vo.system.LoginVo;
import com.atguigu.spzx.service.SysUserService;
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


}
