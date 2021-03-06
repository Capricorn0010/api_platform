package com.chant.api.common.config.security;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static com.chant.api.common.constant.CommonConstant.TOKEN_SECRET;

@Data
@Component
public class JWTConfig {

	@Value("${jwt.expire-time}")
	private Long expireTime;

	@Value("${jwt.iss}")
	private String iss;

	@Value("${jwt.enable}")
	private Boolean enable;

	/**
	 * 私钥
	 */
	private String secret = TOKEN_SECRET;

}
