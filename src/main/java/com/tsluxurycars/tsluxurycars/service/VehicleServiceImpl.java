package com.tsluxurycars.tsluxurycars.service;

import com.tsluxurycars.tsluxurycars.exception.VehicleNotFoundException;
import com.tsluxurycars.tsluxurycars.model.Vehicle;
import com.tsluxurycars.tsluxurycars.repository.VehicleRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {
    private final VehicleRepository vehicleRepository;

    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Vehicle getVehicle(Long id) throws VehicleNotFoundException {
        return vehicleRepository.findById(id).orElseThrow(() -> new VehicleNotFoundException(id));
    }

    public Vehicle createVehicle(Vehicle newVehicle) {
        return vehicleRepository.save(newVehicle);
    }

    // TODO: CAN I ONLY PROVIDE THE UPDATE FIELDS INSTEAD OF THE WHOLE OBJECT?
    public Vehicle updateVehicle(Vehicle newVehicle, Long id) {
        return vehicleRepository.findById(id)
                .map(vehicle -> {
                    vehicle.setMake(newVehicle.getMake());
                    vehicle.setModel(newVehicle.getModel());
                    vehicle.setYear(newVehicle.getYear());
                    vehicle.setEngine(newVehicle.getEngine());
                    vehicle.setPrice(newVehicle.getPrice());
                    return vehicleRepository.save(vehicle);
                })
                .orElseGet(() -> {
                    newVehicle.setId(id);
                    return vehicleRepository.save(newVehicle);
                });
    }

    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }
}
