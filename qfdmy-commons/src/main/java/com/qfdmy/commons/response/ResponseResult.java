package com.qfdmy.commons.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * 通用数据响应结果
 *
 * @author Lusifer
 * @since v1.0.0
 */
@Data
public class ResponseResult implements Serializable {

    private static final long serialVersionUID = -9127050844792378533L;

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 消息
     */
    private String message;

    /**
     * 异常
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String throwable;

    /**
     * 需要返回的数据对象
     */
    private Object data;

    public ResponseResult() {
        super();
    }

    /**
     * 一般的响应结果，不返回数据
     *
     * @param code {@link ResponseCode}
     */
    public ResponseResult(ResponseCode code) {
        super();
        this.code = code.code();
        this.message = code.message();
    }

    /**
     * 一般的响应结果，不返回数据，自定义消息
     *
     * @param code    {@link ResponseCode}
     * @param message {@code String} 自定义消息
     */
    public ResponseResult(ResponseCode code, String message) {
        super();
        this.code = code.code();
        this.message = message;
    }

    /**
     * 一般的响应结果，包含数据
     *
     * @param code {@link ResponseCode}
     * @param data {@code Object}
     */
    public ResponseResult(ResponseCode code, Object data) {
        super();
        this.code = code.code();
        this.message = code.message();
        this.data = data;
    }

    /**
     * 一般的响应结果，自定义消息并包含数据
     *
     * @param code    {@link ResponseCode}
     * @param message {@link String}
     * @param data    {@code Object}
     */
    public ResponseResult(ResponseCode code, String message, Object data) {
        super();
        this.code = code.code();
        this.message = message;
        this.data = data;
    }

    /**
     * 异常的响应结果，返回的对象会包含 {@code throwable} 字段
     *
     * @param code      {@link ResponseCode}
     * @param throwable {@link Throwable}
     */
    public ResponseResult(ResponseCode code, Throwable throwable) {
        super();
        this.code = code.code();
        this.message = code.message();
        this.throwable = throwable.getMessage();
    }

    /**
     * 请求成功
     *
     * @return this
     */
    public static ResponseResult success() {
        return new ResponseResult(ResponseCode.SUCCESS);
    }

    /**
     * 请求成功
     *
     * @param message {@code String} 自定义消息
     * @return this
     */
    public static ResponseResult success(String message) {
        return new ResponseResult(ResponseCode.SUCCESS, message);
    }

    /**
     * 请求成功，包含数据部分
     *
     * @param data {@code Object}
     * @return this
     */
    public static ResponseResult success(Object data) {
        return new ResponseResult(ResponseCode.SUCCESS, data);
    }

    /**
     * 请求成功，自定义状态码，包含数据
     *
     * @param code {@link ResponseCode} 自定义状态码
     * @param data {@code Object}
     * @return this
     */
    public static ResponseResult success(ResponseCode code, Object data) {
        return new ResponseResult(code, data);
    }

    /**
     * 请求成功，自定义消息并包含数据
     *
     * @param message {@link String} 自定义消息
     * @param data    {@code Object}
     * @return this
     */
    public static ResponseResult success(String message, Object data) {
        return new ResponseResult(ResponseCode.SUCCESS, message, data);
    }

    /**
     * 请求失败
     *
     * @return this
     */
    public static ResponseResult failure() {
        return new ResponseResult(ResponseCode.FAILURE);
    }

    /**
     * 请求失败，自定义消息
     *
     * @param message {@code String} 消息
     * @return this
     */
    public static ResponseResult failure(String message) {
        return new ResponseResult(ResponseCode.FAILURE, message);
    }

    /**
     * 请求失败，自定义状态码
     *
     * @param code {@link ResponseCode} 自定义状态码
     * @return this
     */
    public static ResponseResult failure(ResponseCode code) {
        return new ResponseResult(code);
    }

    /**
     * 请求失败，自定义状态码，包含异常消息
     *
     * @param code      {@link ResponseCode} 自定义状态码
     * @param throwable {@link Throwable}
     * @return this
     */
    public static ResponseResult failure(ResponseCode code, Throwable throwable) {
        return new ResponseResult(code, throwable);
    }

    /**
     * 请求失败，自定义状态码，包含数据
     *
     * @param code {@link ResponseCode} 自定义状态码
     * @param data {@code Object}
     * @return this
     */
    public static ResponseResult failure(ResponseCode code, Object data) {
        return new ResponseResult(code, data);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((data == null) ? 0 : data.hashCode());
        result = prime * result + ((message == null) ? 0 : message.hashCode());
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ResponseResult other = (ResponseResult) obj;
        if (data == null) {
            if (other.data != null) {
                return false;
            }
        } else if (!data.equals(other.data)) {
            return false;
        }
        if (message == null) {
            if (other.message != null) {
                return false;
            }
        } else if (!message.equals(other.message)) {
            return false;
        }
        if (code == null) {
            return other.code == null;
        } else {
            return code.equals(other.code);
        }
    }

}
