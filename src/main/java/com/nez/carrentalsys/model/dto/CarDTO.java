package com.nez.carrentalsys.model.dto;

import com.nez.carrentalsys.model.enums.CarStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarDTO {
    private Long id;
    private String brand;
    private String model;
    private int year;
    private String licensePlate;
    private double pricePerDay;
    private CarStatus status;
}
