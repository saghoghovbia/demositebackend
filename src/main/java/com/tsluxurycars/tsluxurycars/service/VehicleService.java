package com.tsluxurycars.tsluxurycars.service;

import com.tsluxurycars.tsluxurycars.exception.VehicleNotFoundException;
import com.tsluxurycars.tsluxurycars.model.Vehicle;

import java.util.List;

public interface VehicleService {
    List<Vehicle> getAllVehicles();
    Vehicle getVehicle(Long id) throws VehicleNotFoundException;
    Vehicle createVehicle(Vehicle newVehicle);
    Vehicle updateVehicle(Vehicle newVehicle, Long id);
    void deleteVehicle(Long id) throws VehicleNotFoundException;
}

