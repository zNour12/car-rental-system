package com.nez.carrentalsys.service;

import com.nez.carrentalsys.model.dto.RentalDTO;
import com.nez.carrentalsys.model.enums.RentalStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RentalService {

    // basic CRUD operations
    List<RentalDTO> getAllRentals();
    Optional<RentalDTO> getRentalById(/*@Param("id")*/ Long id);
    RentalDTO createRental(RentalDTO rentalDTO);
    RentalDTO updateRental(Long id,RentalDTO rentalDTO);
    void deleteRental( Long id);

    // Filter/Search methods
    List<RentalDTO> getRentalByStatus(RentalStatus status);
    List<RentalDTO> getRentalByCarId(Long carId);
    List<RentalDTO> getRentalByCustomerId(Long customerId);
    List<RentalDTO> getOverDueRentals();

    // Business Logic methods
    RentalDTO returnCar(Long rentalId);
    boolean isCarAvailable(Long carId, LocalDate startDate, LocalDate endDate);

}
