package com.nez.carrentalsys.mapper;

import com.nez.carrentalsys.model.dto.CarDTO;
import com.nez.carrentalsys.model.entity.Car;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarMapper {
    CarDTO toDTO(Car car);
    Car toEntity(CarDTO carDTO);
}