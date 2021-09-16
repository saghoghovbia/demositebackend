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

    public List<Vehicle> getAllVehiclesByMake(String make) {
        return vehicleRepository.findAllByMake(make);
    }

    public List<Vehicle> getAllVehiclesByPriceGreaterThanOrEqual(Long price) {
        return vehicleRepository.findAllByPriceIsGreaterThanEqual(price);
    }

    public List<Vehicle> getAllVehiclesByPriceLessThanOrEqual(Long price) {
        return vehicleRepository.findAllByPriceIsLessThanEqual(price);
    }

    public List<Vehicle> getAllVehiclesByYearGreaterThanOrEqual(int year) {
        return vehicleRepository.findAllByYearIsGreaterThanEqual(year);
    }

    public List<Vehicle> getAllVehiclesByYearLessThanOrEqual(int year) {
        return vehicleRepository.findAllByYearIsLessThanEqual(year);
    }

    public List<Vehicle> getAllVehiclesByEngine(String engine) {
        return vehicleRepository.findAllByEngine(engine);
    }

    public Vehicle getVehicle(Long id) throws VehicleNotFoundException {
        return vehicleRepository.findById(id).orElseThrow(() -> new VehicleNotFoundException(id));
    }

    public Vehicle createVehicle(Vehicle newVehicle) {
        return vehicleRepository.save(newVehicle);
    }

    // TODO: SHOULD I ONLY PROVIDE THE UPDATE FIELDS INSTEAD OF THE WHOLE OBJECT?
    public Vehicle updateVehicle(Vehicle newVehicle, Long id) {
        return vehicleRepository.findById(id)
                .map(vehicle -> {
                    vehicle.setMake(newVehicle.getMake());
                    vehicle.setModel(newVehicle.getModel());
                    vehicle.setYear(newVehicle.getYear());
                    vehicle.setEngine(newVehicle.getEngine());
                    vehicle.setPrice(newVehicle.getPrice());
                    vehicle.setImageLink(newVehicle.getImageLink());
                    return vehicleRepository.save(vehicle);
                })
                .orElseGet(() -> {
                    newVehicle.setId(id);
                    return vehicleRepository.save(newVehicle);
                });
    }

    public void deleteVehicle(Long id) throws VehicleNotFoundException {
        if (vehicleRepository.existsById(id)) {
            vehicleRepository.deleteById(id);
        } else {
            throw new VehicleNotFoundException(id);
        }
    }
}
