package com.tsluxurycars.tsluxurycars.controller;

import com.tsluxurycars.tsluxurycars.exception.VehicleNotFoundException;
import com.tsluxurycars.tsluxurycars.model.Vehicle;
import com.tsluxurycars.tsluxurycars.service.VehicleService;
import com.tsluxurycars.tsluxurycars.service.VehicleServiceImpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {
    private final VehicleService vehicleService;

    public VehicleController(VehicleServiceImpl vehicleServiceImpl) {
        this.vehicleService = vehicleServiceImpl;
    }

    @GetMapping("/")
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        return ResponseEntity.ok(vehicleService.getAllVehicles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicle(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(vehicleService.getVehicle(id));
        } catch (VehicleNotFoundException e) {
            e.printStackTrace();
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/")
    public ResponseEntity<Vehicle> createVehicle(@RequestBody Vehicle newVehicle) {
        return new ResponseEntity<>(vehicleService.createVehicle(newVehicle), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vehicle> updateVehicle(@RequestBody Vehicle newVehicle, @PathVariable Long id) {
        return ResponseEntity.ok(vehicleService.updateVehicle(newVehicle, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
        return ResponseEntity.noContent().build();
    }
}
