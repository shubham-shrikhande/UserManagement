package com.nt.controller;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.service.EUserServiceImp;

@RestController
@RequestMapping("/forgetControl")
public class ForgetController {

	@Autowired
	EUserServiceImp service;
	
	@GetMapping("/forget/{email}")
	public String forgetPassword(@PathVariable String email) throws MessagingException {
		return service.forgetPassword(email);
	}
}
