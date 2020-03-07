package com.chant.api.system.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;


@Getter
@AllArgsConstructor
public enum RoleEnum implements GrantedAuthority {

	SUPER_ADMIN(0),

	USER(1),

	;

	private int value;

	@Override
	public String getAuthority() {
		return this.name();
	}
}
