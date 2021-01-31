package com.harshit.warba.service;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import com.harshit.warba.model.Car;
import com.harshit.warba.model.Customer;
import com.harshit.warba.model.Service;
import com.harshit.warba.repository.CarRepository;
import com.harshit.warba.repository.CustomerRepository;
import com.harshit.warba.repository.ServiceRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarServiceTest {

  @Autowired
  private CarService carService;

  @MockBean
  private CustomerRepository custRepo;

  @MockBean
  private CarRepository carRepo;

  @MockBean
  private ServiceRepository servRepo;

  @Test
  public void test_createNewCustomer() {
    Customer cust = new Customer();
    cust.setCars(new ArrayList<Car>());
    cust.setCustName("Test_Customer");

    Mockito.when(custRepo.save(cust)).thenReturn(cust);

    assertEquals(carService.createNewCustomer(cust), cust);
  }

  @Test
  public void test_addCarToCustomer() {
    Customer cust = new Customer();
    cust.setCars(new ArrayList<Car>());
    cust.setCustName("Test_Customer1");

    Car car = new Car();
    car.setCarName("Test_Car1");
    car.setServices(new ArrayList<Service>());

    Optional<Customer> optional = Optional.of(cust);
    Mockito.when(custRepo.findById("CUST_001")).thenReturn(optional);
    Mockito.when(custRepo.save(cust)).thenReturn(cust);
    Mockito.when(carRepo.save(car)).thenReturn(car);

    Car carReturnedFromService = carService.addCarToCustomer("CUST_001", car);

    assertEquals(carReturnedFromService, car);
  }

  @Test
  public void test_getAllCustomers() {
    Customer cust1 = new Customer();
    cust1.setCars(new ArrayList<Car>());
    cust1.setCustName("Test_Customer1");

    Customer cust2 = new Customer();
    cust1.setCars(new ArrayList<Car>());
    cust1.setCustName("Test_Customer2");

    List<Customer> listOfCustomer = new ArrayList<Customer>();
    listOfCustomer.add(cust1);
    listOfCustomer.add(cust2);

    Mockito.when(custRepo.findAll()).thenReturn(listOfCustomer);

    assertEquals(carService.getAllCustomers(), listOfCustomer);
  }

  @Test
  public void test_addService() {
    Car car = new Car();
    car.setCarName("Test_Car1");
    car.setServices(new ArrayList<Service>());

    Service service = new Service();
    service.setDateServiced(new Date());
    service.setServiceNote("Engine Oil Changed");
    service.setCar(car);

    Optional<Car> optional = Optional.of(car);
    Mockito.when(carRepo.findById("CAR_001")).thenReturn(optional);
    Mockito.when(carRepo.save(car)).thenReturn(car);
    Mockito.when(servRepo.save(service)).thenReturn(service);

    Service serviceReturned = carService.addService("CAR_001", service);

    assertEquals(serviceReturned, service);
  }

  @Test
  public void test_findAllServicesByCar() {
    Car car = new Car();
    car.setCarName("Test_Car1");
    car.setServices(new ArrayList<Service>());

    Service service1 = new Service();
    service1.setDateServiced(new Date());
    service1.setServiceNote("Engine Oil Changed");
    service1.setCar(car);

    Service service2 = new Service();
    service2.setDateServiced(new Date());
    service2.setServiceNote("General Service");
    service2.setCar(car);

    car.addService(service1);
    car.addService(service2);

    List<Service> listOfService = new ArrayList<Service>();
    listOfService.add(service1);
    listOfService.add(service2);

    Optional<Car> optional = Optional.of(car);
    Mockito.when(carRepo.findById("CAR_001")).thenReturn(optional);

    assertEquals(carService.findAllServicesByCar("CAR_001"), listOfService);
  }

  @Test
  public void test_findAllServicesByCustomer() {
    Customer cust = new Customer();
    cust.setCars(new ArrayList<Car>());
    cust.setCustName("Test_Customer1");

    Car car = new Car();
    car.setCarName("Test_Car1");
    car.setServices(new ArrayList<Service>());

    Service service1 = new Service();
    service1.setDateServiced(new Date());
    service1.setServiceNote("Engine Oil Changed");
    service1.setCar(car);

    Service service2 = new Service();
    service2.setDateServiced(new Date());
    service2.setServiceNote("General Service");
    service2.setCar(car);

    List<Service> listOfService = new ArrayList<Service>();
    listOfService.add(service1);
    listOfService.add(service2);

    Mockito.when(servRepo.findAllServiceForCustomer("CUST_001")).thenReturn(listOfService);

    assertEquals(carService.findAllServicesByCustomer("CUST_001"), listOfService);
  }
}
