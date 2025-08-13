package com.nez.carrentalsys.mapper;

import com.nez.carrentalsys.model.dto.CustomerDTO;
import com.nez.carrentalsys.model.entity.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerDTO toDTO(Customer customer);
    Customer toEntity(CustomerDTO customerDTO);
}
