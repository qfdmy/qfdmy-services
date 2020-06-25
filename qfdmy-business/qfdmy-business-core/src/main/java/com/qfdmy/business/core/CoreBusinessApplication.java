package com.qfdmy.business.core;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Lusifer
 * @since v1.0.0
 */
@SpringBootApplication
@MapperScan(basePackages = "com.qfdmy.repository.core.mapper")
public class CoreBusinessApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoreBusinessApplication.class, args);
    }

}
