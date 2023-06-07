package com.nt.model;

import lombok.Data;

@Data
public class ActiveAcc {

	private String email;
	private String oldPassword;
	private String newPassword;
	private String confirmNewPassword;
}
