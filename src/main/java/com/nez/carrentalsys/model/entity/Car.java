package com.nez.carrentalsys.model.entity;

import com.nez.carrentalsys.model.enums.CarStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "cars")
@Getter
@Builder
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
    private BigDecimal pricePerDay;

    @Column(name = "car_status")
    @Enumerated(EnumType.STRING)
    private CarStatus status;
}
