package com.chant.api.common.config.security;

import com.chant.api.common.constant.CommonConstant;
import com.chant.api.common.enums.ExceptionEnum;
import com.chant.api.common.model.BusinessException;
import com.chant.api.common.model.SysExceptionJson;
import com.chant.api.common.model.SysSecurityUser;
import com.chant.api.common.service.SecurityUserService;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	private JWTConfig jwtConfig;

	private String jwtSecret;

	private SecurityUserService securityUserService;

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager, String jwtSecret, SecurityUserService securityUserService, JWTConfig jwtConfig) {
		super(authenticationManager);
		this.jwtSecret = jwtSecret;
		this.securityUserService = securityUserService;
		this.jwtConfig = jwtConfig;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request,
	                                HttpServletResponse response,
	                                FilterChain chain) throws IOException, ServletException {
		String tokenHeader = request.getHeader(CommonConstant.TOKEN_HEADER);
		if (tokenHeader == null || !tokenHeader.startsWith(CommonConstant.TOKEN_PREFIX)) {
			chain.doFilter(request, response);
			return;
		}
		try {
			UPAuTokenWithExpiration usernamePasswordAuthenticationToken = getAuthentication(tokenHeader);
			if (jwtConfig.getExpireTime() != null
					/*&& usernamePasswordAuthenticationToken.getExpiration() != null
					&& new Date(System.currentTimeMillis() + (jwtConfig.getExpireTime() / 2)).after(usernamePasswordAuthenticationToken.getExpiration())*/) {
				//现在时间 + 半衰期 > 有效时间
				response.setHeader(CommonConstant.TOKEN_REFRESH, "true");
			}
			// 如果请求头中有token，则进行解析，并且设置认证信息
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			super.doFilterInternal(request, response, chain);
		} catch (AuthenticationException e) {
			this.onUnsuccessfulAuthentication(request, response, e);
		}
	}

	@Override
	protected void onUnsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {
		response.setStatus(ExceptionEnum.TOKEN_ERROR.getStatus());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		try {
			response.getWriter().write(new SysExceptionJson(ExceptionEnum.TOKEN_ERROR).toJSONString());
		} catch (IOException e) {
			throw new BusinessException(ExceptionEnum.SERVER_ERROR);
		}
	}

	// 这里从token中获取用户信息并新建一个token
	private UPAuTokenWithExpiration getAuthentication(String tokenHeader) {
		String token = tokenHeader.replace(CommonConstant.TOKEN_PREFIX, "");
		try {
			Claims claims = JWTAuthUtil.getTokenBody(token, jwtSecret);
			if (StringUtils.isBlank(claims.getSubject())) {
				return null;
			}
			if (claims.getIssuer().equals(jwtConfig.getIss())) {
				String name = claims.getSubject();
				SysSecurityUser userDetails = securityUserService.getByUserName(name);
				if (userDetails != null) {
					UserContextHelper.setUser(userDetails);
					return new UPAuTokenWithExpiration(name, null, userDetails.getAuthorities()/*, claims.getExpiration()*/);
				}
			}
			return null;
		} catch (Exception e) {
			throw new BadCredentialsException(e.getMessage(), e);
		}

	}

}
