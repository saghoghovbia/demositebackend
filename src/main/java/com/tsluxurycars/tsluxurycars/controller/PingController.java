package com.tsluxurycars.tsluxurycars.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("Pong!");
    }

    @GetMapping("/secure")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("You hit a secure endpoint");
    }
}
