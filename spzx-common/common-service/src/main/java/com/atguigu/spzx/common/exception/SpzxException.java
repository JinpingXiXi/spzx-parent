package com.atguigu.spzx.common.exception;

import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import lombok.Data;

@Data
public class SpzxException extends RuntimeException{
    private Integer code;
    private String msg;
    public ResultCodeEnum resultCodeEnum;
    public SpzxException(ResultCodeEnum resultCodeEnum){
        code = resultCodeEnum.getCode();
        msg = resultCodeEnum.getMessage();
        this.resultCodeEnum = resultCodeEnum;
    }
}
