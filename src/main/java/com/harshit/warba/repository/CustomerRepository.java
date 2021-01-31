package com.harshit.warba.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harshit.warba.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {

}
