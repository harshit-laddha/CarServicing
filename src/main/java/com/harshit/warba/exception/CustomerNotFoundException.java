package com.harshit.warba.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Customer Not Found")
public class CustomerNotFoundException extends RuntimeException {

	private String custID;

	public CustomerNotFoundException(String custID) {
		super();
		this.custID = custID;
	}

	@Override
	public String getMessage() {
		return "Customer not Found with ID : " + custID;
	}

}
