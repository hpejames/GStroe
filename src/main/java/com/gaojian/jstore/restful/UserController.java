package com.gaojian.jstore.restful;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gaojian.jstore.restful.bean.User;

@RestController
public class UserController {

	@RequestMapping(value="/getUser", method = RequestMethod.POST)
	public User getUser(@RequestBody User users) {
		User user = new User();
		user.setUserName("gaojian");
		user.setPassword("gaojian123");
		return user;
	}
}
