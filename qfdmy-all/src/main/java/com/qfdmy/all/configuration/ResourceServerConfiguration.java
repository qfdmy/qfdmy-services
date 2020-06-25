package com.qfdmy.all.configuration;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * 资源服务器配置
 *
 * @author Lusifer
 * @since v1.0.0
 */
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    /**
     * 管理员角色
     */
    private static final String ADMIN = "ADMIN";

    /**
     * 用户角色
     */
    private static final String USERS = "USERS";

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 允许访问全部资源
//		http.exceptionHandling().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//				.and().authorizeRequests().antMatchers("/**").permitAll();

        // 管理员授权请求路径
        String[] adminPaths = new String[]{
                "/core/**", "/qiniu/**"
        };

        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry
                expressionInterceptUrlRegistry = http.exceptionHandling()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests();
        for (String adminPath : adminPaths) {
            expressionInterceptUrlRegistry.antMatchers(adminPath).hasAnyAuthority(ADMIN);
        }
    }

}
