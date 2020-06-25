package com.qfdmy.repository.core.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.qfdmy.commons.base.BaseDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 管理员表
 *
 * @author Lusifer
 * @since v1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CoreAdmin extends BaseDomain {

    private static final long serialVersionUID = 6502271318209839396L;

    /**
     * 账号
     */
    @NotBlank(message = "账号为必填项")
    private String username;

    /**
     * 密码
     */
    @NotNull(message = "密码长度在 6 - 20 位字符之间")
    private String password;

    /**
     * 昵称
     */
    @NotBlank(message = "昵称为必填项")
    private String nickname;

    /**
     * 邮箱
     */
    @NotBlank(message = "邮箱地址为必填项")
    @Email(message = "请输入正确的邮箱地址")
    private String email;

    /**
     * 用户状态：1(已启用) 0(已禁用)
     */
    private Integer status;

    /**
     * 逻辑删除：1(已删除) 0(未删除)
     */
    @TableField(value = "is_deleted", fill = FieldFill.INSERT)
    private Boolean deleted;

}
