package com.qfdmy.oauth.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * 认证服务器配置
 *
 * @author Lusifer
 * @since v1.0.0
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

	/**
	 * 注入用于支持 password 模式
	 */
	@Resource
	private AuthenticationManager authenticationManager;

	/**
	 * 默认的加密方式
	 * @return {@link BCryptPasswordEncoder}
	 */
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * Refresh Token 时需要自定义实现，否则抛异常 <br>
	 * Lazy 注解是为了防止循环注入（is there an unresolvable circular reference?）
	 */
	@Lazy
	@Resource(name = "userDetailsServiceBean")
	private UserDetailsService userDetailsService;

	@Bean
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource dataSource() {
		// 配置数据源（注意，我使用的是 HikariCP 连接池），以上注解是指定数据源，否则会有冲突
		return DataSourceBuilder.create().build();
	}

	@Bean
	public TokenStore tokenStore() {
		// 基于 JDBC 实现，令牌保存到数据库
		return new JdbcTokenStore(dataSource());
	}

	@Bean
	public ClientDetailsService jdbcClientDetailsService() {
		// 基于 JDBC 实现，需要事先在数据库配置客户端信息
		return new JdbcClientDetailsService(dataSource());
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints
				// 用于支持密码模式
				.authenticationManager(authenticationManager).tokenStore(tokenStore());

		// Refresh Token 时需要自定义实现，否则抛异常
		// Handling error: IllegalStateException, UserDetailsService is required.
		endpoints.userDetailsService(userDetailsService);
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security
				// 允许客户端访问 /oauth/check_token 检查 token
				.checkTokenAccess("isAuthenticated()").allowFormAuthenticationForClients();
	}

	/**
	 * 配置客户端
	 * @param clients {@link ClientDetailsServiceConfigurer}
	 * @throws Exception 全局异常
	 */
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		// 客户端配置
		clients.withClientDetails(jdbcClientDetailsService());
	}

}
