package com.chant.api.common.model;


import com.chant.api.system.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * security验证用户model
 *

 **/

@Data
@EqualsAndHashCode(callSuper = false)
public class SysSecurityUser extends User implements UserDetails {

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton(this.getRole());
	}

	@Override
	public String getUsername() {
		return this.getName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
