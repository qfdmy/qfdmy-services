package com.qfdmy.cloud.qiniu.configuration;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 七牛云配置
 * @author Lusifer
 * @since v1.0.0
 */
@Configuration
@EnableConfigurationProperties(value = QiniuProperties.class)
public class QiniuConfiguration {

}
