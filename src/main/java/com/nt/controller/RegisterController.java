package com.nt.controller;

import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.model.MUser;
import com.nt.service.EUserServiceImp;

@RestController
@RequestMapping("/regiControl")
public class RegisterController {

	@Autowired
	EUserServiceImp service;

	@GetMapping("/checkemail/{email}")
	public String checkEmailPresentOrNot(@PathVariable String email) {
		String msg = service.getUserByEmail(email);
		return msg;
	}

	@GetMapping("/getCountries")
	public Map<Integer, String> getAllCountries() {
		Map<Integer, String> countries = service.getAllCountries();
		return countries;
	}

	@GetMapping("/getStates/{countryId}")
	public Map<Integer, String> getAllStates(@PathVariable Integer countryId) {
		Map<Integer, String> states = service.getAllStates(countryId);
		return states;
	}

	@GetMapping("/getCities/{stateId}")
	public Map<Integer, String> getAllCities(@PathVariable Integer stateId) {
		Map<Integer, String> cities = service.getAllCitiesById(stateId);
		return cities;
	}

	@PostMapping("/register")
	public String registerUser(@RequestBody MUser user) throws MessagingException {
		String msg = service.registerUser(user);
		return msg;
	}
}
