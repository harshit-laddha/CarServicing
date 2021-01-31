package com.harshit.warba.controller;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.harshit.warba.model.Car;
import com.harshit.warba.model.Customer;
import com.harshit.warba.model.Service;
import com.harshit.warba.service.CarService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CarServiceController.class)
public class CarServiceControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  CarService carService;

  @Test
  public void test_createNewCustomer() throws Exception {
    Customer cust = new Customer();
    cust.setCars(new ArrayList<Car>());
    cust.setCustName("Test_Customer");

    String inputInJson = mapToJson(cust);

    String uri = "/carservice/createCustomer";

    Mockito.when(carService.createNewCustomer(Mockito.any(Customer.class))).thenReturn(cust);

    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.post(uri).accept(MediaType.APPLICATION_JSON).content(inputInJson)
            .contentType(MediaType.APPLICATION_JSON);

    MvcResult result = mockMvc.perform(requestBuilder).andReturn();
    MockHttpServletResponse response = result.getResponse();

    String outputInJson = response.getContentAsString();

    assertEquals(inputInJson, outputInJson);
    assertEquals(HttpStatus.OK.value(), response.getStatus());
  }

  @Test
  public void test_getAllCustomers() throws Exception {
    Customer cust1 = new Customer();
    cust1.setCars(new ArrayList<Car>());
    cust1.setCustName("Test_Customer1");

    Customer cust2 = new Customer();
    cust1.setCars(new ArrayList<Car>());
    cust1.setCustName("Test_Customer2");

    List<Customer> listOfCustomer = new ArrayList<Customer>();
    listOfCustomer.add(cust1);
    listOfCustomer.add(cust2);

    String expectedJson = mapToJson(listOfCustomer);

    String uri = "/carservice/findAllCustomer";

    Mockito.when(carService.getAllCustomers()).thenReturn(listOfCustomer);

    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON);

    MvcResult result = mockMvc.perform(requestBuilder).andReturn();
    MockHttpServletResponse response = result.getResponse();

    String outputInJson = response.getContentAsString();

    assertEquals(expectedJson, outputInJson);
    assertEquals(HttpStatus.OK.value(), response.getStatus());
  }

  @Test
  public void test_addCar() throws Exception {
    Car car = new Car();
    car.setCarName("Test_Car1");
    car.setServices(new ArrayList<Service>());

    String inputInJson = mapToJson(car);

    String uri = "/carservice/addCar/CUST_001";

    Mockito.when(carService.addCarToCustomer(Mockito.anyString(), Mockito.any(Car.class)))
        .thenReturn(car);

    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.post(uri).accept(MediaType.APPLICATION_JSON).content(inputInJson)
            .contentType(MediaType.APPLICATION_JSON);

    MvcResult result = mockMvc.perform(requestBuilder).andReturn();
    MockHttpServletResponse response = result.getResponse();

    String outputInJson = response.getContentAsString();

    assertEquals(inputInJson, outputInJson);
    assertEquals(HttpStatus.OK.value(), response.getStatus());
  }

  @Test
  public void test_addService() throws Exception {
    Service service = new Service();
    service.setServiceNote("Engine Oil Changed");

    String inputInJson = mapToJson(service);

    String uri = "/carservice/addService/CAR_001";

    Mockito.when(carService.addService(Mockito.anyString(), Mockito.any(Service.class)))
        .thenReturn(service);

    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.post(uri).accept(MediaType.APPLICATION_JSON).content(inputInJson)
            .contentType(MediaType.APPLICATION_JSON);

    MvcResult result = mockMvc.perform(requestBuilder).andReturn();
    MockHttpServletResponse response = result.getResponse();

    String outputInJson = response.getContentAsString();

    assertEquals(inputInJson, outputInJson);
    assertEquals(HttpStatus.OK.value(), response.getStatus());
  }

  @Test
  public void test_getAllServiceForCar() throws Exception {
    Car car = new Car();
    car.setCarName("Test_Car1");
    car.setServices(new ArrayList<Service>());

    Service service1 = new Service();
    service1.setServiceNote("Engine Oil Changed");
    service1.setCar(car);

    Service service2 = new Service();
    service2.setServiceNote("General Service");
    service2.setCar(car);

    car.addService(service1);
    car.addService(service2);

    List<Service> listOfService = new ArrayList<Service>();
    listOfService.add(service1);
    listOfService.add(service2);

    String expectedJson = mapToJson(listOfService);

    String uri = "/carservice/findServiceByCar/CAR_001";

    Mockito.when(carService.findAllServicesByCar(Mockito.anyString())).thenReturn(listOfService);

    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON);

    MvcResult result = mockMvc.perform(requestBuilder).andReturn();
    MockHttpServletResponse response = result.getResponse();

    String outputInJson = response.getContentAsString();

    assertEquals(expectedJson, outputInJson);
    assertEquals(HttpStatus.OK.value(), response.getStatus());
  }

  @Test
  public void test_getAllServiceForCustomer() throws Exception {
    Customer cust = new Customer();
    cust.setCars(new ArrayList<Car>());
    cust.setCustName("Test_Customer1");

    Car car = new Car();
    car.setCarName("Test_Car1");
    car.setServices(new ArrayList<Service>());

    Service service1 = new Service();
    service1.setServiceNote("Engine Oil Changed");
    service1.setCar(car);

    Service service2 = new Service();
    service2.setServiceNote("General Service");
    service2.setCar(car);

    List<Service> listOfService = new ArrayList<Service>();
    listOfService.add(service1);
    listOfService.add(service2);

    String uri = "/carservice/findServiceByCustomer/CUST_001";

    String expectedJson = mapToJson(listOfService);

    Mockito.when(carService.findAllServicesByCustomer(Mockito.anyString()))
        .thenReturn(listOfService);

    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON);

    MvcResult result = mockMvc.perform(requestBuilder).andReturn();
    MockHttpServletResponse response = result.getResponse();

    String outputInJson = response.getContentAsString();

    assertEquals(expectedJson, outputInJson);
    assertEquals(HttpStatus.OK.value(), response.getStatus());
  }

  private String mapToJson(Object object) throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.writeValueAsString(object);
  }
}
