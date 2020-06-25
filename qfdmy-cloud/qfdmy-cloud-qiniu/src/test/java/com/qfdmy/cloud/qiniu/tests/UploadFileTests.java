package com.qfdmy.cloud.qiniu.tests;

import com.qfdmy.cloud.qiniu.QiniuCloudApplication;
import com.qfdmy.cloud.qiniu.storage.service.IUploadFileService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = QiniuCloudApplication.class)
public class UploadFileTests {

    @Resource
    private IUploadFileService uploadFileService;

    @Test
    public void testUploadImage() {
        System.out.println(uploadFileService.putImage(new File("D:\\test.png")));
    }
}
