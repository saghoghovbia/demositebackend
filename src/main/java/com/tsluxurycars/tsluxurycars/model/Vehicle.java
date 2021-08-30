package com.tsluxurycars.tsluxurycars.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.data.annotation.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {
    @Id
    private Long id;

    private String make;

    private String model;

    private int year;

    private String engine;

    private double price;
}
