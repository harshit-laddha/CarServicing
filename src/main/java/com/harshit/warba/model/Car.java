package com.harshit.warba.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.harshit.warba.generators.CustomIDGenerator;

import lombok.Data;

@Entity
@Data
public class Car {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "car_seq")
	@GenericGenerator(name = "car_seq", strategy = "com.harshit.warba.generators.CustomIDGenerator", parameters = {
			@Parameter(name = CustomIDGenerator.VALUE_PREFIX_PARAMETER, value = "CAR_"),
			@Parameter(name = CustomIDGenerator.NUMBER_FORMAT_PARAMETER, value = "%03d") })
	private String carID;

	private String carName;

	@OneToMany
	@JoinTable(joinColumns = @JoinColumn(name = "CAR_ID"), inverseJoinColumns = @JoinColumn(name = "SERVICE_ID"))
	Collection<Service> services = new ArrayList<Service>();

	@ManyToOne
	@JoinColumn(name = "CUST_ID")
	private Customer customer;

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public String getCarID() {
		return carID;
	}

	public Collection<Service> getServices() {
		return services;
	}

	public void setServices(Collection<Service> services) {
		this.services = services;
	}

	public void addService(Service service) {
		this.services.add(service);
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
