package com.nez.carrentalsys.repository;

import com.nez.carrentalsys.model.entity.Car;
import com.nez.carrentalsys.model.entity.Rental;
import com.nez.carrentalsys.model.enums.RentalStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {

    List<Rental> findByStatus(RentalStatus status);

    List<Rental> findByCarId(Long carId);

    List<Rental> findByCustomerId(Long customerId);

    List<Rental> findByCarIdAndCarStatus(Long carId, RentalStatus status);

    @Query("SELECT r FROM Rental r WHERE r.status = 'ACTIVE' AND r.endDate < :currentDate")
    List<Rental> findOverDueRentals(@Param("currentDate") LocalDate currentDate);
    Long car(Car car);
}
