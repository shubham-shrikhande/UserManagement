package com.nt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.model.LoginModel;
import com.nt.service.EUserServiceImp;

@RestController
@RequestMapping("/loginControl")
public class LoginController {

	@Autowired
	private EUserServiceImp service;

	@GetMapping("/checkemail/{email}")
	public String checkEmailPresentOrNot(@PathVariable String email) {
		String msg = service.getUserByEmail(email);
		return msg;
	}

	@PostMapping("/login")
	public String SignInUser(@RequestBody LoginModel user) {
		String msg=service.loginUser(user);
		return msg;
	}
}
