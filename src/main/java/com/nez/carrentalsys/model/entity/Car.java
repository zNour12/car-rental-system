package com.nez.carrentalsys.model.entity;

import com.nez.carrentalsys.model.enums.CarStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cars")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "car_brand")
    private String brand;

    @Column(name = "car_model")
    private String model;

    @Column(name = "car_year")
    private int year;

    @Column(name = "car_license_plate")
    private String licensePlate;

    @Column(name = "car_price_per_day")
    private double pricePerDay;

    @Column(name = "car_status")
    @Enumerated(EnumType.STRING)
    private CarStatus status;
}
