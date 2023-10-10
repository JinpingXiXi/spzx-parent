package com.atguigu.spzx.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

//读取 spzx.auth 开头的所有配置
@ConfigurationProperties(prefix = "spzx.auth")
@Data
public class AuthProperties {
    private List<String> noAuthUrls;
}
