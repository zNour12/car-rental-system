package com.nez.carrentalsys.service;

import com.nez.carrentalsys.model.dto.CarDTO;
import com.nez.carrentalsys.model.enums.CarStatus;

import java.util.List;
import java.util.Optional;

public interface CarService {
    List<CarDTO> getAllCars();
    Optional<CarDTO> getCarById(Long id);
    List<CarDTO> getCarsByStatus(CarStatus status);
    CarDTO saveCar(CarDTO carDTO);
    CarDTO updateCar(Long id, CarDTO carDTO);
    void deleteCar(Long id);
}
