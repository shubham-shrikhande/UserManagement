package com.nt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.model.ActiveAcc;
import com.nt.service.EUserServiceImp;

@RestController
@RequestMapping("/accControl")
public class AccActiveController {

	@Autowired
	EUserServiceImp service;
	
	@PostMapping("/activeacc")
	public String get(@RequestBody ActiveAcc acc) {
		return service.validateActivationForm(acc);
	}
}
