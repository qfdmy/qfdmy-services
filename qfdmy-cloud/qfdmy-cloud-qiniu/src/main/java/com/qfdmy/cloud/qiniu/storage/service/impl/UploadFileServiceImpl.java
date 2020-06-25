package com.qfdmy.cloud.qiniu.storage.service.impl;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.util.StrUtil;
import com.google.gson.Gson;
import com.qfdmy.cloud.qiniu.configuration.QiniuConfig;
import com.qfdmy.cloud.qiniu.configuration.QiniuProperties;
import com.qfdmy.cloud.qiniu.storage.service.IUploadFileService;
import com.qfdmy.commons.exceptions.BusinessException;
import com.qfdmy.commons.response.ResponseCode;
import com.qfdmy.commons.tools.ImageTool;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.InputStream;

/**
 * 文件上传实现
 *
 * @author Lusifer
 * @since v1.0.0
 */
@Slf4j
@Service
public class UploadFileServiceImpl implements IUploadFileService {

    @Resource
    private QiniuProperties properties;

    private String uploadToken;
    private UploadManager uploadManager;

    /**
     * 默认不指定 key 的情况下，以文件内容的 hash 值作为文件名
     */
    private String key = null;

    @Override
    public String putImage(File file) {
        return putImage(file, QiniuConfig.IMAGE_STYLE_JPG);
    }

    @Override
    public String putImage(File file, String style) {
        // 判断文件真实类型
        String type = FileTypeUtil.getType(file);
        // 生成新的文件名
        String filename = StrUtil.uuid().replace("-", "") + "." + type;
        // 判断是否为指定图片类型
        if (ImageTool.isImage(filename)) {
            // 上传
            return put(this.key, file, style);
        }

        throw new BusinessException(ResponseCode.IMAGE_IS_WRONG);
    }

    /**
     * 上传
     *
     * @param key    {@code String} 可以是路径 + 文件名 + 后缀的形式，如：/images/java/hello.png
     * @param object 允许的类型为：{@code byte[]}、{@code String}、{@code File}、{@code InputStream}
     * @param style  七牛云预定义的图片样式如 {@link QiniuConfig#IMAGE_STYLE_JPG}
     * @return
     */
    private String put(String key, Object object, String style) {
        // 认证
        uploadToken();

        Response response = null;

        try {
            if (object instanceof byte[]) {
                response = uploadManager.put((byte[]) object, key, uploadToken);
            }
            else if (object instanceof String) {
                response = uploadManager.put((String) object, key, uploadToken);
            }
            else if (object instanceof File) {
                response = uploadManager.put((File) object, key, uploadToken);
            }
            else if (object instanceof InputStream) {
                response = uploadManager.put((InputStream) object, key, uploadToken, null, null);
            }

            if (null != response) {
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                // 生成后的访问路径会走 CDN 并经过图片处理接口
                // 如：http://images.qfdmy.com/uuid.jpg@.webp 这里的 .webp 是在七牛云上设置的图片样式名
                return String.format("%s/%s@%s", properties.getCdn(), putRet.key, style);
            }
        } catch (QiniuException ex) {
            Response r = ex.response;
            throw new BusinessException(r.toString());
        }

        return null;
    }

    /**
     * 认证七牛云
     * <p>
     * 注意：实际情况是七牛云认证会过期，所以每次请求都需要认证一次 <br>
     * 当然也可以先认证一次，抓异常信息如果发现过期再验证
     * </p>
     */
    private void uploadToken() {
        Auth auth = Auth.create(properties.getAccessKey(), properties.getSecretKey());
        Configuration cfg = new Configuration(Region.huanan());

        this.uploadManager = new UploadManager(cfg);
        this.uploadToken = auth.uploadToken(properties.getStorage().getBucketName());
    }
}
