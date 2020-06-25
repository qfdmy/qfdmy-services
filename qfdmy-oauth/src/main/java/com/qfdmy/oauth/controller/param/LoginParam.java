package com.qfdmy.oauth.controller.param;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 登录参数
 * @author Lusifer
 * @since v1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class LoginParam implements Serializable {

    private static final long serialVersionUID = 6227804428105653962L;

    /**
     * 账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

}
