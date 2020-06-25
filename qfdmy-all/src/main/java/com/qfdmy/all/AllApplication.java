package com.qfdmy.all;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * 千锋达摩院单体应用打包
 *
 * @author Lusifer
 * @since v1.0.0
 */
@SpringBootApplication(scanBasePackages = {"com.qfdmy"})
@MapperScan(basePackages = "com.qfdmy.repository.**.mapper")
public class AllApplication {

    /**
     * MyBatis Plus 分页插件
     *
     * @return {@link PaginationInterceptor}
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    public static void main(String[] args) {
        SpringApplication.run(AllApplication.class, args);
    }

}
