package com.gabriel.SchoolManagement.exception.user;

import com.gabriel.SchoolManagement.exception.ResourceNotFoundException;
import com.gabriel.SchoolManagement.exception.ValidationException;

public class UserNotFoundException extends ResourceNotFoundException {
	public UserNotFoundException() {
		super("User not found");
	}
}
