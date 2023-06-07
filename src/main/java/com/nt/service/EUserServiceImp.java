package com.nt.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.nt.entity.EUser;
import com.nt.entity.UserCity;
import com.nt.entity.UserCountry;
import com.nt.entity.UserState;
import com.nt.model.ActiveAcc;
import com.nt.model.LoginModel;
import com.nt.model.MUser;
import com.nt.repository.CityRepository;
import com.nt.repository.CountryRepository;
import com.nt.repository.StateRepository;
import com.nt.repository.UEntityRepository;

@Service
public class EUserServiceImp implements IUserService {

	@Autowired
	UEntityRepository repo;
	@Autowired
	CountryRepository countryrepo;
	@Autowired
	StateRepository staterepo;
	@Autowired
	CityRepository cityrepo;
	@Autowired
	JavaMailSender sender;
	@Value("${spring.mail.username}")
	private String fromMail;

	@Override
	public String getUserByEmail(String email) {

		EUser user = repo.getUserByEmail(email);
		if (user != null) {
			String msg = repo.getStatusByEmail(email);
			if (msg.equalsIgnoreCase("active")) {
				return "Account is registered and " + msg;
			} else {
				return " Account is registered but not " + msg;
			}

		}
		return email + " is not registered !!";
	}

	@Override
	public Map<Integer, String> getAllCountries() {
		List<UserCountry> list = countryrepo.findAll();
		Map<Integer, String> map = new TreeMap<Integer, String>();
		for (UserCountry country : list) {
			map.put(country.getCountryId(), country.getCountryName());
		}
		return map;
	}

	@Override
	public Map<Integer, String> getAllStates(Integer countryId) {
		List<UserState> list = staterepo.findStatesByCountryId(countryId);
		Map<Integer, String> map = new TreeMap<Integer, String>();
		for (UserState state : list) {
			map.put(state.getStateId(), state.getStateName());
		}
		return map;
	}

	@Override
	public Map<Integer, String> getAllCitiesById(Integer stateId) {
		List<UserCity> list = cityrepo.findCitiesByStateId(stateId);
		Map<Integer, String> map = new TreeMap<Integer, String>();
		for (UserCity city : list) {
			map.put(city.getCityId(), city.getCityName());
		}
		return map;
	}

	@Override
	public String registerUser(MUser user) throws MessagingException {
		EUser usere = new EUser();
		System.out.println(user);
		usere.setPassword(generateRandomPassword());
		usere.setAccStatus("inactive");
		BeanUtils.copyProperties(user, usere);
		System.out.println(usere);
		repo.save(usere);
		String subject = "Regarding account activation.";
		String random = generateRandomPassword();
		String link = "http://localhost:8080/NOT FIXED YET";
		String body = "Hello " + usere.getFname() + ",\n Your temporary password is \n " + random
				+ " \n Please click on the link to active the account by setting password.\n" + link + "\n Thank you.";
		String msg = sendEmail(fromMail, user.getEmail(), subject, body);
		return msg;
	}

	private static String generateRandomPassword() {
		// Define the range of characters to choose from
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

		// Initialize the random number generator
		Random random = new Random();

		// Create a StringBuilder to build the password
		StringBuilder password = new StringBuilder();

		// Generate 6 random characters
		for (int i = 0; i < 6; i++) {
			// Generate a random index within the range of characters
			int randomIndex = random.nextInt(characters.length());

			// Append the randomly selected character to the password
			password.append(characters.charAt(randomIndex));
		}

		// Convert the StringBuilder to a String and return the password
		return password.toString();
	}

	private String sendEmail(String fromMail, String toMail, String subject, String body) throws MessagingException {
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom(fromMail);
		helper.setCc(toMail);
		helper.setSubject(subject);
		helper.setSentDate(new Date());
		helper.setText(body);

		sender.send(message);
		return "Mail sent successfully!!";
	}

	@Override
	public String validateActivationForm(ActiveAcc acc) {

		EUser user = repo.getUserByEmail(acc.getEmail());
		if (!user.getPassword().equals(acc.getOldPassword())) {
			return "Password entered is not matching with email password!!";
		} else if (!acc.getNewPassword().equals(acc.getConfirmNewPassword())) {
			return "Your Password is not matching !!";
		}
		String msg = activeAccount(acc);

		return msg;
	}

	private String activeAccount(ActiveAcc acc) {
		EUser user = repo.getUserByEmail(acc.getEmail());
		user.setAccStatus("active");
		user.setPassword(acc.getNewPassword());
		repo.save(user);
		return "Account unlocked, please proceed with login";
	}

	@Override
	public String forgetPassword(String email) throws MessagingException {
		EUser user = repo.getUserByEmail(email);
		if (user == null) {
			return "No such user is in record. ";
		}
		String subject = "Forget password.";
		String body = "Hello " + user.getFname() + ",\n Please check your password below\n" + user.getPassword();
		sendEmail(fromMail, email, subject, body);
		return "Password is sent on your register email id. Please check your email id.";
	}

	@Override
	public String loginUser(LoginModel user) {
		String password = repo.getPasswordByEmail(user.getEmail());
		if (user.getPassword().equals(password)) {
			return "Login Successfull !!";
		}

		return "Invalid password";
	}

}
