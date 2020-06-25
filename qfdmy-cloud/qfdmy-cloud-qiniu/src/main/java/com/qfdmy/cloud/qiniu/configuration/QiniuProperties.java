package com.qfdmy.cloud.qiniu.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 七牛云配置
 * @author Lusifer
 * @since v1.0.0
 */
@Data
@ConfigurationProperties(prefix = "qfdmy.cloud.qiniu")
public class QiniuProperties {
    private String cdn = "";
    private String accessKey = "";
    private String secretKey = "";
    private Storage storage = new Storage();

    @Data
    public static class Storage {
        private String bucketName = "";
    }
}
