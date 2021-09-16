package com.tsluxurycars.tsluxurycars.controller;

import com.tsluxurycars.tsluxurycars.constants.Role;
import com.tsluxurycars.tsluxurycars.exception.AuthUserNotFoundException;
import com.tsluxurycars.tsluxurycars.exception.VehicleNotFoundException;
import com.tsluxurycars.tsluxurycars.model.AuthUser;
import com.tsluxurycars.tsluxurycars.model.Vehicle;
import com.tsluxurycars.tsluxurycars.service.AuthUserService;
import com.tsluxurycars.tsluxurycars.service.AuthUserServiceImpl;
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
    private final AuthUserService authUserService;

    public VehicleController(VehicleServiceImpl vehicleServiceImpl, AuthUserServiceImpl authUserService) {
        this.vehicleService = vehicleServiceImpl;
        this.authUserService = authUserService;
    }

    @GetMapping("")
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        return ResponseEntity.ok(vehicleService.getAllVehicles());
    }

    @GetMapping("/make/{make}")
    public ResponseEntity<List<Vehicle>> getAllVehiclesByMake(@PathVariable String make) {
        return ResponseEntity.ok(vehicleService.getAllVehiclesByMake(make));
    }

    @GetMapping("/price/{price}")
    public ResponseEntity<List<Vehicle>> getAllVehiclesByPrice(@PathVariable Long price,
                                                                                 @RequestParam String filter) {
        if (filter.equalsIgnoreCase("greater")) {
            return ResponseEntity.ok(vehicleService.getAllVehiclesByPriceGreaterThanOrEqual(price));
        } else if (filter.equalsIgnoreCase("less")) {
            return ResponseEntity.ok(vehicleService.getAllVehiclesByPriceLessThanOrEqual(price));
        }

        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/year/{year}")
    public ResponseEntity<List<Vehicle>> getAllVehiclesByYear(@PathVariable int year,
                                                               @RequestParam String filter) {
        if (filter.equalsIgnoreCase("greater")) {
            return ResponseEntity.ok(vehicleService.getAllVehiclesByYearGreaterThanOrEqual(year));
        } else if (filter.equalsIgnoreCase("less")) {
            return ResponseEntity.ok(vehicleService.getAllVehiclesByYearLessThanOrEqual(year));
        }

        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/{engine}")
    public ResponseEntity<List<Vehicle>> getAllVehiclesByEngine(@PathVariable String engine) {
        return ResponseEntity.ok(vehicleService.getAllVehiclesByEngine(engine));
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

    @PostMapping("")
    public ResponseEntity<Vehicle> createVehicle(@RequestBody Vehicle newVehicle, @RequestParam Long userID) {
        AuthUser authUser = null;

        try {
            authUser = authUserService.findById(userID);
        } catch (AuthUserNotFoundException e) {
            e.printStackTrace();
        }

        if (authUser == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (authUser.getRole() == Role.ADMIN) {
            return new ResponseEntity<>(vehicleService.createVehicle(newVehicle), HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vehicle> updateVehicle(@RequestBody Vehicle newVehicle, @PathVariable Long id, @RequestParam Long userID) {
        AuthUser authUser = null;

        try {
            authUser = authUserService.findById(userID);
        } catch (AuthUserNotFoundException e) {
            e.printStackTrace();
        }

        if (authUser == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (authUser.getRole() == Role.ADMIN) {
            return ResponseEntity.ok(vehicleService.updateVehicle(newVehicle, id));
        }

        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id, @RequestParam Long userID) {
        AuthUser authUser = null;

        try {
            authUser = authUserService.findById(userID);
        } catch (AuthUserNotFoundException e) {
            e.printStackTrace();
        }

        if (authUser == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (authUser.getRole() == Role.ADMIN) {
            try {
                vehicleService.deleteVehicle(id);
            } catch (VehicleNotFoundException e) {
                e.printStackTrace();

                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return ResponseEntity.noContent().build();
        }

        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}
