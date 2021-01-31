# CarServicing
The application would keep record of the customers, their car(s), and the individual services and service notes associated with each car.

List of API:
1. Create a Customer: 
	URL : http://localhost:8080/carservice/createCustomer
	Request Type : POST
	HEADER BODY:
		{
    			"custName": "Harshit"

		}
	
    

2. List All Customer :
	URL : http://localhost:8080/carservice/findAllCustomer
	Request Type : GET
	
3. Create and assign car to Customer :
	URL : http://localhost:8080/carservice/addCar/CUST_001
	Request Type : POST
	HEADER BODY:
		{
    			"carName":"Velar"
		}
4. Record Service for a Car :
	URL : http://localhost:8080/carservice/addService/CAR_001
	Request Type : POST
	HEADER BODY:
		{
   			 "serviceNote":"Engine Change"
		}
5. List all services for a specific car :
	URL : http://localhost:8080/carservice/findServiceByCar/CAR_001
	Request Type : GET
6. List all services for a specific customer :
	URL : http://localhost:8080/carservice/findServiceByCustomer/CUST_001
	Request Type : GET
	
7. SWAGGER-API Docs :
	URL : http://localhost:8080/v2/api-docs
	Request Type : GET
	
8. SWAGGER HTML URL : http://localhost:8080/swagger-ui.html



