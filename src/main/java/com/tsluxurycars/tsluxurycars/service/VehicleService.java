package com.tsluxurycars.tsluxurycars.service;

import com.tsluxurycars.tsluxurycars.exception.VehicleNotFoundException;
import com.tsluxurycars.tsluxurycars.model.Vehicle;

import java.util.List;

public interface VehicleService {
    List<Vehicle> getAllVehicles();
    List<Vehicle> getAllVehiclesByMake(String make);
    List<Vehicle> getAllVehiclesByPriceGreaterThanOrEqual(Long price);
    List<Vehicle> getAllVehiclesByPriceLessThanOrEqual(Long price);
    List<Vehicle> getAllVehiclesByYearGreaterThanOrEqual(int year);
    List<Vehicle> getAllVehiclesByYearLessThanOrEqual(int year);
    List<Vehicle> getAllVehiclesByEngine(String engine);
    Vehicle getVehicle(Long id) throws VehicleNotFoundException;
    Vehicle createVehicle(Vehicle newVehicle);
    Vehicle updateVehicle(Vehicle newVehicle, Long id);
    void deleteVehicle(Long id) throws VehicleNotFoundException;
}

