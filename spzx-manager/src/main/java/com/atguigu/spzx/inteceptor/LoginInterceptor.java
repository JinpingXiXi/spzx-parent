package com.atguigu.spzx.inteceptor;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.atguigu.spzx.common.exception.SpzxException;
import com.atguigu.spzx.model.entity.system.SysUser;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class LoginInterceptor {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        // ALLOW ALL PREFLIGHT
        String requestMethod = request.getMethod();
        if (requestMethod.equals("OPTIONS")) {
            return true;
        }

        String token  = request.getHeader("token");
        String userJsonStr = redisTemplate.opsForValue().get("user:login:" + token);
        if (StrUtil.isEmpty(userJsonStr)) {
            throw new SpzxException(ResultCodeEnum.LOGIN_AUTH);
        }
        //get object:sysUser from json string
        SysUser sysUser = JSON.parseObject(userJsonStr, SysUser.class);



    }
}
