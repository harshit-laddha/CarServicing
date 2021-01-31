package com.harshit.warba.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.harshit.warba.model.Service;

public interface ServiceRepository extends JpaRepository<Service, String> {

  @Query(
      value = "SELECT * FROM SERVICE S INNER JOIN car_services C ON S.SERVICEID = C.SERVICE_ID INNER JOIN customer_cars CU ON CU.CAR_ID = C.CAR_ID WHERE CU.CUST_ID = :custid",
      nativeQuery = true)
  List<Service> findAllServiceForCustomer(@Param("custid") String customer);
}
