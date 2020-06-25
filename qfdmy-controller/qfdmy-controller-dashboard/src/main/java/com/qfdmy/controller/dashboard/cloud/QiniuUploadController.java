package com.qfdmy.controller.dashboard.cloud;

import cn.hutool.core.io.FileUtil;
import com.qfdmy.cloud.qiniu.configuration.QiniuConfig;
import com.qfdmy.cloud.qiniu.storage.domain.QiniuBody;
import com.qfdmy.cloud.qiniu.storage.service.IUploadFileService;
import com.qfdmy.commons.response.ResponseResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;

/**
 * 七牛云文件上传
 *
 * @author Lusifer
 * @since v1.0.0
 */
@RestController
@RequestMapping("qiniu/upload")
public class QiniuUploadController {

    @Resource
    private IUploadFileService uploadFileService;

    @PostMapping("image")
    public ResponseResult image(MultipartFile image, MultipartFile file) {
        String url = null;

        // 创建临时目录
        File tmpDir = FileUtil.mkdir("/tmp");
        // 创建临时文件
        File tempFile = FileUtil.createTempFile(tmpDir);

        // 将输入流转换为文件类型
        try {
            if (null != image) {
                image.transferTo(tempFile);
                url = uploadFileService.putImage(tempFile);
            }
            else if (null != file) {
                file.transferTo(tempFile);
                url = uploadFileService.putImage(tempFile, QiniuConfig.IMAGE_STYLE_PNG);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 封装 URL 地址返回给前端
        if (null != url) {
            QiniuBody returnBody = new QiniuBody();
            returnBody.setUrl(url);
            return ResponseResult.success(returnBody);
        }

        return null;
    }

}
