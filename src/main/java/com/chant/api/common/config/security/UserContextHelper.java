package com.chant.api.common.config.security;


import com.chant.api.common.model.SysSecurityUser;

public class UserContextHelper {

	private static final ThreadLocal<SysSecurityUser> userThreadLocal = new ThreadLocal<>();

	public static SysSecurityUser getUser() {
		return userThreadLocal.get();
	}

	public static SysSecurityUser setUser(SysSecurityUser user) {
		userThreadLocal.set(user);
		return user;
	}

	public static void remove() {
		userThreadLocal.remove();
	}

}
