package com.atguigu.spzx.common.exception;

import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//全局异常处理器
@RestControllerAdvice
public class GlobalExceptionHandler {

    //捕获所有异常
    @ExceptionHandler(Exception.class)
    public Result<T> handler(Exception e){
        //打印异常信息
        e.printStackTrace();
        //响应数据给前端
        return Result.error(ResultCodeEnum.SYSTEM_ERROR);
    }

    //捕获指定异常
    @ExceptionHandler(NullPointerException.class)
    public Result<T> handler(NullPointerException e){
        //打印异常信息
        e.printStackTrace();
        //响应数据给前端
        return Result.error(ResultCodeEnum.NULL_ERROR);
    }

    //捕获自定义异常
    @ExceptionHandler(GuiguException.class)
    public Result<T> handler(GuiguException e){
        //打印异常信息
        e.printStackTrace();
        //响应数据给前端
        return Result.error(e.getResultCodeEnum());
    }
}
