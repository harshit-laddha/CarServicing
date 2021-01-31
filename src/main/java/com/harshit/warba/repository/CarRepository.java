package com.harshit.warba.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harshit.warba.model.Car;

public interface CarRepository extends JpaRepository<Car, String> {

}
