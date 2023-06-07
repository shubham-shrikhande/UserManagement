package com.nt.service;

import java.util.Map;

import javax.mail.MessagingException;

import com.nt.model.ActiveAcc;
import com.nt.model.LoginModel;
import com.nt.model.MUser;

public interface IUserService {

	public String getUserByEmail(String email);

	public Map<Integer, String> getAllCountries();
	public Map<Integer, String> getAllStates(Integer countryId);
	public Map<Integer,String> getAllCitiesById(Integer stateId);
	public String registerUser(MUser user) throws MessagingException;

	public String validateActivationForm(ActiveAcc acc);
	public String forgetPassword(String email) throws MessagingException;
	public String loginUser(LoginModel user);
}
