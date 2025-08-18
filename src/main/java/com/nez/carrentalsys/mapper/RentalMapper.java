package com.nez.carrentalsys.mapper;

import com.nez.carrentalsys.model.dto.RentalDTO;
import com.nez.carrentalsys.model.entity.Rental;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RentalMapper {

    @Mapping(source = "car.id", target = "carId")
    @Mapping(source = "customer.id", target = "customerId")
    RentalDTO toDTO(Rental rental);

    @Mapping(source = "carId", target = "car.id")
    @Mapping(source = "customerId", target = "customer.id")
    Rental toEntity(RentalDTO rentalDTO);
}
