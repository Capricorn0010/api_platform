package com.chant.api.system.controller;

import com.chant.api.system.entity.User;
import com.chant.api.system.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Slf4j
@Api(tags = "Backend: 用户")
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@ApiOperation("测试单记录")
	@GetMapping("/{id}")
	public User getById(@PathVariable @ApiParam(value = "id", example = "1" ,required = true) Long id) {
		return new User().setName("user");
	}




}

