package com.tsluxurycars.tsluxurycars.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Vehicle {
    public Vehicle(String make, String model, int year, String engine, Long price) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.engine = engine;
        this.price = price;
    }

    @Id
    @GeneratedValue
    private Long id;

    private String make;

    private String model;

    private int year;

    private String engine;

    private Long price;
}
