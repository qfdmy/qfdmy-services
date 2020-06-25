package com.qfdmy.controller.dashboard;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Lusifer
 * @since v1.0.0
 */
@SpringBootApplication(scanBasePackages = "com.qfdmy")
@MapperScan(basePackages = "com.qfdmy.repository.core.mapper")
public class DashboardControllerApplication {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public static void main(String[] args) {
        SpringApplication.run(DashboardControllerApplication.class, args);
    }

}
