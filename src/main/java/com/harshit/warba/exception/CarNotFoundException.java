package com.harshit.warba.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Car Not Found")
public class CarNotFoundException extends RuntimeException {

	private String carID;

	public CarNotFoundException(String carID) {
		super();
		this.carID = carID;
	}

	@Override
	public String getMessage() {
		return "carID not Found with ID : " + carID;
	}
}
