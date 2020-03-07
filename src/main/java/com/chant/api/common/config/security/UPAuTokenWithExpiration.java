package com.chant.api.common.config.security;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import java.util.Collection;
import java.util.Date;

/**
 * token过期
 *

 **/

public class UPAuTokenWithExpiration extends UsernamePasswordAuthenticationToken {

//	private Date expiration;

	public UPAuTokenWithExpiration(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
		super(principal, credentials, authorities);
//		this.expiration = expiration;
	}
}
