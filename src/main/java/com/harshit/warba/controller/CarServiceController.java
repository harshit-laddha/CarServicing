package com.harshit.warba.controller;

import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.harshit.warba.model.Car;
import com.harshit.warba.model.Customer;
import com.harshit.warba.model.Service;
import com.harshit.warba.service.CarService;

@RestController
@RequestMapping("/carservice")
public class CarServiceController {

  @Autowired
  CarService carService;

  @GetMapping(value = "/findAllCustomer", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Customer> getAllCustomers() {
    return carService.getAllCustomers();
  }

  @PostMapping(value = "/createCustomer", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public Customer createNewCustomer(@RequestBody Customer cust) {
    return carService.createNewCustomer(cust);
  }

  @PostMapping(value = "/addCar/{custID}", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public Car addCar(@PathVariable String custID, @RequestBody Car car) {
    return carService.addCarToCustomer(custID, car);
  }

  @PostMapping(value = "/addService/{carID}", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public Service addService(@PathVariable String carID, @RequestBody Service service) {
    return carService.addService(carID, service);
  }

  @GetMapping(value = "/findServiceByCar/{carID}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Collection<Service> getAllServiceForCar(@PathVariable String carID) {
    return carService.findAllServicesByCar(carID);
  }

  @GetMapping(value = "/findServiceByCustomer/{custID}",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public Collection<Service> getAllServiceForCustomer(@PathVariable String custID) {
    return carService.findAllServicesByCustomer(custID);
  }
}
