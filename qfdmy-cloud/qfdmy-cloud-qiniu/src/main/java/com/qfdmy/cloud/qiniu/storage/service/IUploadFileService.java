package com.qfdmy.cloud.qiniu.storage.service;

import java.io.File;

/**
 * 文件上传服务
 * @author Lusifer
 * @since v1.0.0
 */
public interface IUploadFileService {

    /**
     * 上传图片，默认样式为 .jpg
     * @param file {@link File}
     * @return {@code String} url
     */
    String putImage(File file);

    /**
     * 上传图片
     * @param file {@link File}
     * @param style {@code String} 七牛云定义的图片样式
     * @return {@code String} url
     */
    String putImage(File file, String style);
}
