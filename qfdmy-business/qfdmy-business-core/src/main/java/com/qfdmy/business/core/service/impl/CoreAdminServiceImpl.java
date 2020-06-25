package com.qfdmy.business.core.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qfdmy.business.core.service.ICoreAdminService;
import com.qfdmy.commons.base.BaseServiceImpl;
import com.qfdmy.commons.exceptions.BusinessException;
import com.qfdmy.commons.response.ResponseCode;
import com.qfdmy.repository.core.domain.CoreAdmin;
import com.qfdmy.repository.core.mapper.CoreAdminMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 管理员表服务实现类
 *
 * @author Lusifer
 * @since v1.0.0
 */
@Service
public class CoreAdminServiceImpl extends BaseServiceImpl<CoreAdminMapper, CoreAdmin> implements ICoreAdminService {

    @Resource
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * 检查字段：账号
     */
    private static final String USERNAME = "username";

    /**
     * 检查字段：昵称
     */
    private static final String NICKNAME = "nickname";

    /**
     * 检查字段：邮箱
     */
    private static final String EMAIL = "email";

    @Override
    public boolean create(CoreAdmin coreAdmin) {
        if (!checkUsername(coreAdmin.getUsername())
                && !checkNickname(coreAdmin.getNickname())
                && !checkEmail(coreAdmin.getEmail())) {
            coreAdmin.setPassword(passwordEncoder.encode(coreAdmin.getPassword()));
            return super.save(coreAdmin);
        }

        return false;
    }

    @Override
    public boolean update(CoreAdmin coreAdmin) {
        CoreAdmin oldCoreAdmin = get(coreAdmin.getId());
        // 有输入密码则加密
        if (StrUtil.isNotBlank(coreAdmin.getPassword())) {
            coreAdmin.setPassword(passwordEncoder.encode(coreAdmin.getPassword()));
        }
        // 否则延用原密码
        else {
            coreAdmin.setPassword(oldCoreAdmin.getPassword());
        }
        return super.update(coreAdmin);
    }

    @Override
    public IPage<CoreAdmin> page(int current, int size, CoreAdmin coreAdmin) {
        Page<CoreAdmin> page = new Page<>(current, size);

        // 查询条件
        LambdaQueryWrapper<CoreAdmin> wrapper = new LambdaQueryWrapper<>();
        wrapper
                .eq(StringUtils.isNotBlank(String.valueOf(coreAdmin.getId())), CoreAdmin::getId, coreAdmin.getId())
                .or().like(StringUtils.isNotBlank(coreAdmin.getUsername()), CoreAdmin::getUsername, coreAdmin.getUsername())
                .or().like(StringUtils.isNotBlank(coreAdmin.getNickname()), CoreAdmin::getNickname, coreAdmin.getNickname())
                .or().like(StringUtils.isNotBlank(coreAdmin.getEmail()), CoreAdmin::getEmail, coreAdmin.getEmail());

        return super.page(page, wrapper);
    }

    // 私有方法 ------------------------------------------- Begin

    /**
     * 检查账号是否存在
     *
     * @param username {@code String} 账号
     * @return {@code boolean} 账号已存在则抛出异常
     */
    private boolean checkUsername(String username) {
        if (checkUniqueness(USERNAME, username)) {
            throw new BusinessException(ResponseCode.USER_HAS_EXISTED);
        }
        return false;
    }

    /**
     * 检查昵称是否存在
     *
     * @param nickname {@code String} 昵称
     * @return {@code boolean} 昵称已存在则抛出异常
     */
    private boolean checkNickname(String nickname) {
        if (checkUniqueness(NICKNAME, nickname)) {
            throw new BusinessException(ResponseCode.USER_NICK_HAS_EXISTED);
        }
        return false;
    }

    /**
     * 检查邮箱是否存在
     *
     * @param email {@code String} 邮箱
     * @return {@code boolean} 邮箱已存在则抛出异常
     */
    private boolean checkEmail(String email) {
        if (checkUniqueness(EMAIL, email)) {
            throw new BusinessException(ResponseCode.USER_EMAIL_HAS_EXISTED);
        }
        return false;
    }
}
