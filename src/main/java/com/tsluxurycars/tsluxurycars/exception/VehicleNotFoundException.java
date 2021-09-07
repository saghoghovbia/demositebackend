package com.tsluxurycars.tsluxurycars.exception;

public class VehicleNotFoundException extends Throwable {
    public VehicleNotFoundException(Long id) {
        super("Vehicle not found with ID: " + id);
    }
}
