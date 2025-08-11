package com.nez.carrentalsys.controller;

import com.nez.carrentalsys.model.dto.CarDTO;
import com.nez.carrentalsys.model.enums.CarStatus;
import com.nez.carrentalsys.service.CarService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @GetMapping
    public ResponseEntity<List<CarDTO>> getAllCars() {
        List<CarDTO> cars = carService.getAllCars();
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarDTO> getCarById(@PathVariable Long id) {
        Optional<CarDTO> car = carService.getCarById(id);
        return car.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<CarDTO>> getCarsByStatus(@PathVariable CarStatus status) {
        List<CarDTO> cars = carService.getCarsByStatus(status);
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/brand/{brand}")
    public ResponseEntity<List<CarDTO>> getCarsByBrand(@PathVariable String brand) {
        List<CarDTO> cars = carService.getCarsByBrand(brand);
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/year/{year}")
    public ResponseEntity<List<CarDTO>> getCarsByYear(@PathVariable int year) {
        List<CarDTO> cars = carService.getCarsByYear(year);
        return ResponseEntity.ok(cars);
    }

    @PostMapping
    public ResponseEntity<CarDTO> createCar(@Valid @RequestBody CarDTO carDTO) {
        CarDTO savedCar =  carService.saveCar(carDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCar);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarDTO> updateCar(@PathVariable Long id, @Valid @RequestBody CarDTO carDTO) {
        CarDTO updatedCar = carService.updateCar(id, carDTO);
        return ResponseEntity.ok(updatedCar);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CarDTO> deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }
}
