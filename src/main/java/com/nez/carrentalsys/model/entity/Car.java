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

    private String brand;
    private String model;

    @Column(name = "car_year")
    private int year;
    private String licensePlate;
    private double pricePerDay;

    @Enumerated(EnumType.STRING)
    private CarStatus status;
}
