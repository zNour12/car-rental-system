package com.nez.carrentalsys.repository;

import com.nez.carrentalsys.model.entity.Car;
import com.nez.carrentalsys.model.enums.CarStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {
    List<Car> findByStatus(CarStatus status);
    List<Car> findByBrand(String brand);
    List<Car> findByYear(int year );
}
