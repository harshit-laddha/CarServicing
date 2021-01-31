package com.harshit.warba.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.harshit.warba.exception.CarNotFoundException;
import com.harshit.warba.exception.CustomerNotFoundException;
import com.harshit.warba.model.Car;
import com.harshit.warba.model.Customer;
import com.harshit.warba.model.Service;
import com.harshit.warba.repository.CarRepository;
import com.harshit.warba.repository.CustomerRepository;
import com.harshit.warba.repository.ServiceRepository;

@org.springframework.stereotype.Service
public class CarService {

	@Autowired
	CustomerRepository custRepo;

	@Autowired
	CarRepository carRepo;

	@Autowired
	ServiceRepository servRepo;

	public List<Customer> getAllCustomers() {
		return custRepo.findAll();
	}

	public Customer createNewCustomer(Customer cust) {
		return custRepo.save(cust);
	}

	public Car addCarToCustomer(String custID, Car car) {
		Optional<Customer> optional = custRepo.findById(custID);
		if (optional.isPresent()) {
			Customer customer = optional.get();
			car.setCustomer(customer);
			Car newCar = carRepo.save(car);
			customer.addCar(newCar);
			custRepo.save(customer);
			return newCar;
		} else {
			throw new CustomerNotFoundException(custID);
		}
	}

	public Service addService(String carID, Service service) {
		Optional<Car> optional = carRepo.findById(carID);
		if (optional.isPresent()) {
			Car car = optional.get();
			service.setCar(car);
			service.setDateServiced(new Date());
			Service newService = servRepo.save(service);
			car.addService(newService);
			carRepo.save(car);
			return service;
		} else {
			throw new CarNotFoundException(carID);
		}
	}

	public Collection<Service> findAllServicesByCar(String carID) {
		Optional<Car> optional = carRepo.findById(carID);
		if (optional.isPresent()) {
			Car car = optional.get();
			return car.getServices();
		} else {
			throw new CarNotFoundException(carID);
		}
	}

	public Collection<Service> findAllServicesByCustomer(String custID) {
		return servRepo.findAllServiceForCustomer(custID);
	}
}
