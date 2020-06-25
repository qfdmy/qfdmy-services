package com.qfdmy.oauth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 认证与授权
 *
 * @author Lusifer
 * @since v1.0.0
 */
@SpringBootApplication(scanBasePackages = "com.qfdmy")
@MapperScan(basePackages = "com.qfdmy.repository.core.mapper")
public class AuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }

}
