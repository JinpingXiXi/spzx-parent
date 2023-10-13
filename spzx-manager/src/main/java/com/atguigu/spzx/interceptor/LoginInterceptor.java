package com.atguigu.spzx.interceptor;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.atguigu.spzx.common.AuthContextUtil;
import com.atguigu.spzx.common.exception.GuiguException;
import com.atguigu.spzx.model.entity.system.SysUser;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //如果是OPTIONS预检请求，则直接放行
        if(request.getMethod().equals("OPTIONS")){
            return true;
        }

        //获取请求头中传递的token令牌
        String token = request.getHeader("token");
        //到Redis中判断token令牌是否存在
        String string = redisTemplate.opsForValue().get("user:login:" + token);
        if(StrUtil.isEmpty(string)){
            //token令牌不存在 > 未登录 > 抛出异常,拦截请求
            throw new GuiguException(ResultCodeEnum.LOGIN_AUTH);
        }

        //将json字符串转为SysUser对象
        SysUser sysUser = JSON.parseObject(string, SysUser.class);
        //保存到线程变量中
        AuthContextUtil.set(sysUser);

        //续期，将token令牌的有限期重置为30分钟
        //redisTemplate.expire("user:login:" + token, 30, TimeUnit.MINUTES);

        //临时代码-将token令牌持久化
        redisTemplate.persist("user:login:" + token);

        //放行，将请求交给Controller
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //Controller的请求响应之后，清除线程变量中的对象，节省内存
        AuthContextUtil.remove();
    }
}
