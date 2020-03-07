package com.chant.api.common.config.security;

import com.chant.api.common.service.SecurityUserService;
import com.chant.api.system.enums.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * security配置
 *

 **/

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter  {

	@Autowired
	private JWTConfig jwtConfig;
	@Autowired
	private SecurityUserService securityUserService;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfigurationSource source =   new UrlBasedCorsConfigurationSource();
		CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
		corsConfiguration.addExposedHeader("Authorization");
		corsConfiguration.addExposedHeader("X-Refresh-Token");
		corsConfiguration.addAllowedMethod("*");
		((UrlBasedCorsConfigurationSource) source).registerCorsConfiguration("/**",corsConfiguration);
		return source;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		if (!jwtConfig.getEnable()) {
			http.cors().and().csrf().disable().authorizeRequests().anyRequest().permitAll();
			return;
		}
		http.cors().and().csrf().disable().authorizeRequests()
				// 放行
				.antMatchers( "/swagger*", "/swagger-resources/**", "/v2/api-docs", "/webjars/**",  "/static/**").permitAll()
				.antMatchers( "/**").hasAnyAuthority(RoleEnum.SUPER_ADMIN.getAuthority())
				// 需求有token登录
				.anyRequest().authenticated()
				.and()
				.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtConfig.getIss(), jwtConfig.getSecret(), jwtConfig.getExpireTime()))
				.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtConfig.getSecret(), securityUserService, jwtConfig))
				.exceptionHandling().authenticationEntryPoint(new MyAuthenticationEntryPoint())
				.and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(securityUserService).passwordEncoder(passwordEncoder);
	}


}
