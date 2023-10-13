package com.atguigu.spzx;

import com.atguigu.spzx.properties.AuthProperties;
import com.atguigu.spzx.properties.MinioProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//启用属性类（将指定的属性类添加到Spring容器中）
@EnableConfigurationProperties({AuthProperties.class, MinioProperties.class})
@EnableTransactionManagement //启用事务管理器
@SpringBootApplication
public class ManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManagerApplication.class, args);
    }
}
