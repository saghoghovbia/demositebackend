package com.tsluxurycars.tsluxurycars.exception;

public class AuthUserNotFoundException extends Throwable {
    public AuthUserNotFoundException(Long id) {
        super("User not found with ID: " + id);
    }
}
