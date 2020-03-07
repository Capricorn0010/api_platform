package com.chant.api.common.service;

import com.chant.api.common.model.SysSecurityUser;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface SecurityUserService extends UserDetailsService {

    SysSecurityUser getByUserName(String name);

}
