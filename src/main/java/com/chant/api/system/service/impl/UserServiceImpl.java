package com.chant.api.system.service.impl;

import com.chant.api.system.entity.User;
import com.chant.api.system.enums.RoleEnum;
import com.chant.api.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User getByName(String name) {
		User user = new User().setName("user").setRole(RoleEnum.SUPER_ADMIN).setPassword(passwordEncoder.encode("123456"));
		return user;
	}

}
