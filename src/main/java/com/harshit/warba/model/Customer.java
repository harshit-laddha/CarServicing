package com.harshit.warba.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.harshit.warba.generators.CustomIDGenerator;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cust_seq")
	@GenericGenerator(name = "cust_seq", strategy = "com.harshit.warba.generators.CustomIDGenerator", parameters = {
			@Parameter(name = CustomIDGenerator.VALUE_PREFIX_PARAMETER, value = "CUST_"),
			@Parameter(name = CustomIDGenerator.NUMBER_FORMAT_PARAMETER, value = "%03d") })
	private String custID;

	private String custName;

	@OneToMany
	@JoinTable(joinColumns = @JoinColumn(name = "CUST_ID"), inverseJoinColumns = @JoinColumn(name = "CAR_ID"))
	Collection<Car> cars = new ArrayList<Car>();

	public Collection<Car> getCars() {
		return cars;
	}

	public void setCars(Collection<Car> cars) {
		this.cars = cars;
	}

	public void addCar(Car car) {
		this.cars.add(car);
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCustID() {
		return custID;
	}
}
