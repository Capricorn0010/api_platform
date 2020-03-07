package com.chant.api.common.service.impl;

import com.chant.api.common.model.SysSecurityUser;
import com.chant.api.common.service.SecurityUserService;
import com.chant.api.system.entity.User;
import com.chant.api.system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SecurityUserServiceImpl implements SecurityUserService {

	@Autowired
	private UserService userService;

	@Override
    public SysSecurityUser getByUserName(String name) throws UsernameNotFoundException {
        User user = userService.getByName(name);
        if (user == null || null == user.getRole()) {
            throw new UsernameNotFoundException(String.format("用户: %s 不存在!", name));
        }
        SysSecurityUser securityUserModel = new SysSecurityUser();
        BeanUtils.copyProperties(user, securityUserModel);
        return securityUserModel;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return getByUserName(s);
    }
}
