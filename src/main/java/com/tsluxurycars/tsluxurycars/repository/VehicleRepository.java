package com.tsluxurycars.tsluxurycars.repository;

import com.tsluxurycars.tsluxurycars.model.Vehicle;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findAllByMake(String make);
    List<Vehicle> findAllByPriceIsGreaterThanEqual(Long price);
    List<Vehicle> findAllByPriceIsLessThanEqual(Long price);
    List<Vehicle> findAllByYearIsGreaterThanEqual(int year);
    List<Vehicle> findAllByYearIsLessThanEqual(int year);
    List<Vehicle> findAllByEngine(String engine);
}
