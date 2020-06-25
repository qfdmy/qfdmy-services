package com.qfdmy.cloud.qiniu.storage.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 七牛云对象管理自定义返回值
 *
 * @author Lusifer
 * @since v1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class QiniuBody implements Serializable {
    private static final long serialVersionUID = 1369729105458165449L;

    /**
     * 七牛云返回的地址
     */
    private String url;
}
