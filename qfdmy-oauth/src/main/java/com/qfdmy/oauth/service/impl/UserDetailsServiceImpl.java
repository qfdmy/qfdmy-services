package com.qfdmy.oauth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.qfdmy.repository.core.domain.CoreAdmin;
import com.qfdmy.repository.core.domain.CoreUser;
import com.qfdmy.repository.core.mapper.CoreAdminMapper;
import com.qfdmy.repository.core.mapper.CoreUserMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 自定义认证与授权
 * @author Lusifer
 * @since v1.0.0
 */
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private CoreAdminMapper coreAdminMapper;

    @Resource
    private CoreUserMapper coreUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 管理后台
        LambdaQueryWrapper<CoreAdmin> adminWrapper = new LambdaQueryWrapper<>();
        adminWrapper.eq(CoreAdmin::getUsername, username);
        CoreAdmin coreAdmin = coreAdminMapper.selectOne(adminWrapper);
        if (null != coreAdmin) {
            // 授权，管理员权限为 ADMIN
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            grantedAuthorities.add(new SimpleGrantedAuthority("ADMIN"));

            // 由框架完成认证工作
            return new User(coreAdmin.getUsername(), coreAdmin.getPassword(), grantedAuthorities);
        }

        // 门户网站
        LambdaQueryWrapper<CoreUser> userWrapper = new LambdaQueryWrapper<>();
        userWrapper.eq(CoreUser::getUsername, username);
        CoreUser coreUser = coreUserMapper.selectOne(userWrapper);
        if (null != coreUser) {
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            grantedAuthorities.add(new SimpleGrantedAuthority("USERS"));
            return new User(coreUser.getUsername(), coreUser.getPassword(), grantedAuthorities);
        }

        return null;
    }
}
