package com.qfdmy.commons.web;

import cn.hutool.core.util.StrUtil;
import com.qfdmy.commons.exceptions.BusinessException;
import com.qfdmy.commons.response.ResponseCode;

/**
 * 请求头处理
 * @author Lusifer
 * @since v1.0.0
 */
public class Header {

    private static final String AUTHORIZATION_BEARER_TOKEN = "Basic ";

    /**
     * 获取 Token
     * @param header {@code String} request.getHeader("Authorization")
     * @return {@code String} token
     */
    public static String getAuthorization(String header) {
        if (StrUtil.isBlank(header) || header.startsWith(AUTHORIZATION_BEARER_TOKEN)) {
            throw new BusinessException(ResponseCode.USER_NOT_LOGGED_IN);
        }
        return header.substring(AUTHORIZATION_BEARER_TOKEN.length() + 1);
    }
}
