package com.nez.carrentalsys.service.Impl;

import com.nez.carrentalsys.mapper.CarMapper;
import com.nez.carrentalsys.model.dto.CarDTO;
import com.nez.carrentalsys.model.entity.Car;
import com.nez.carrentalsys.model.enums.CarStatus;
import com.nez.carrentalsys.repository.CarRepository;
import com.nez.carrentalsys.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final CarMapper carMapper;

    @Override
    public List<CarDTO> getAllCars() {
        return carRepository.findAll().stream().map(carMapper::toDTO).toList();
    }

    @Override
    public Optional<CarDTO> getCarById(Long id) {
        return carRepository.findById(id).map(carMapper::toDTO);
    }

    @Override
    public List<CarDTO> getCarsByStatus(CarStatus status) {
        return carRepository.findByStatus(status).stream().map(carMapper::toDTO).toList();
    }

    @Override
    public List<CarDTO> getCarsByBrand(String brand) {
        return carRepository.findByBrand(brand).stream().map(carMapper::toDTO).toList();
    }

    @Override
    public List<CarDTO> getCarsByYear(int year) {
        return carRepository.findByYear(year).stream().map(carMapper::toDTO).toList();
    }

    @Override
    public CarDTO saveCar(CarDTO carDTO){
        Car car = carMapper.toEntity(carDTO);
        Car savedCar = carRepository.save(car);
        return carMapper.toDTO(savedCar);
    }

    @Override
    public CarDTO updateCar(Long id, CarDTO carDTO) {
        carDTO.builder()
                .id(id);
        Car car = carMapper.toEntity(carDTO);
        Car updatedCar = carRepository.save(car);
        return carMapper.toDTO(updatedCar);
    }

    @Override
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }
}
