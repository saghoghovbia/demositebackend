package com.tsluxurycars.tsluxurycars.service;

import com.tsluxurycars.tsluxurycars.exception.VehicleNotFoundException;
import com.tsluxurycars.tsluxurycars.model.Vehicle;
import com.tsluxurycars.tsluxurycars.repository.VehicleRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@ExtendWith(MockitoExtension.class)
class VehicleServiceImplTest {
    @Mock
    private VehicleRepository vehicleRepository;

    @InjectMocks
    private VehicleServiceImpl vehicleService;

    @Test
    void getAllVehicles() {
        Mockito.when(vehicleRepository.findAll()).thenReturn(Arrays.asList(new Vehicle(), new Vehicle()));

        assertThat(vehicleService.getAllVehicles(), is(notNullValue()));
    }

    @Test
    void getAllVehiclesByMake() {
        Vehicle vehicle = new Vehicle("Porsche", "911", 2008, "V8", 65000L, null);

        Mockito.when(vehicleRepository.findAllByMake("Porsche")).thenReturn(List.of(vehicle));

        assertThat(vehicleService.getAllVehiclesByMake("Porsche").get(0).getMake(), is("Porsche"));
    }

    @Test
    void getVehicle() throws VehicleNotFoundException {
        Long id = 1L;
        Vehicle vehicle = new Vehicle();

        Mockito.when(vehicleRepository.findById(id)).thenReturn(Optional.of(vehicle));

        assertThat(vehicleService.getVehicle(id), is(vehicle));
    }

    @Test
    void getVehicle_VehicleNotFound() {
        Long id = 1L;

        Mockito.when(vehicleRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(VehicleNotFoundException.class, () -> {
            vehicleService.getVehicle(id);
        });
    }

    @Test
    void createVehicle() {
        Mockito.when(vehicleRepository.save(Mockito.any(Vehicle.class))).thenReturn(new Vehicle());

        assertThat(vehicleService.createVehicle(new Vehicle()), is(notNullValue()));
    }

    @Test
    void updateVehicle() {
        Long id = 1L;

        Vehicle vehicle = new Vehicle("Porsche", "911", 2008, "V8", 65000L, null);
        vehicle.setId(id);
        Vehicle newVehicle = new Vehicle("Porsche", "911", 2010, "V8", 65000L, null);

        Mockito.when(vehicleRepository.findById(id)).thenReturn(Optional.of(vehicle));

        vehicleService.updateVehicle(newVehicle, id);

        assertThat(vehicle.getYear(), is(2010));
    }

    @Test
    void deleteVehicle() throws VehicleNotFoundException {
        Mockito.when(vehicleRepository.existsById(Mockito.any(Long.class))).thenReturn(true);
        Mockito.doNothing().when(vehicleRepository).deleteById(Mockito.any(Long.class));

        vehicleService.deleteVehicle(Mockito.any(Long.class));

        Mockito.verify(vehicleRepository).deleteById(Mockito.any(Long.class));
    }

    @Test
    void deleteVehicle_VehicleNotFound() {
        Mockito.when(vehicleRepository.existsById(Mockito.any(Long.class))).thenReturn(false);

        assertThrows(VehicleNotFoundException.class, () -> {
            vehicleService.deleteVehicle(Mockito.any(Long.class));
        });

    }
}