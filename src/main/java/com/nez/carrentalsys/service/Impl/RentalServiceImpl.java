package com.nez.carrentalsys.service.Impl;

import com.nez.carrentalsys.mapper.RentalMapper;
import com.nez.carrentalsys.model.dto.RentalDTO;
import com.nez.carrentalsys.model.entity.Car;
import com.nez.carrentalsys.model.entity.Customer;
import com.nez.carrentalsys.model.entity.Rental;
import com.nez.carrentalsys.model.enums.CarStatus;
import com.nez.carrentalsys.model.enums.RentalStatus;
import com.nez.carrentalsys.repository.CarRepository;
import com.nez.carrentalsys.repository.CustomerRepository;
import com.nez.carrentalsys.repository.RentalRepository;
import com.nez.carrentalsys.service.RentalService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RentalServiceImpl implements RentalService {

    // Declarations
    private final RentalRepository rentalRepository;
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;
    private final RentalMapper rentalMapper;

    // Helper methods
    private boolean datesOverlap(LocalDate existingStart, LocalDate existingEnd, LocalDate newStart, LocalDate newEnd) {
        // 2 date ranges would overlap if:
        // newStart is before existingEnd, and newEnd is after existingStart
        return  newStart.isBefore(existingEnd.plusDays(1))
                &&
                newEnd.isAfter(existingStart.minusDays(1));
    }

    private BigDecimal calculatedRentalCost(BigDecimal pricePerDay, LocalDate startDate, LocalDate endDate) {
        long days = ChronoUnit.DAYS.between(startDate, endDate);

        // ensure minimum 1 dat rental
        if (days <= 0) {
            days = 1;
        }
        return pricePerDay.multiply(BigDecimal.valueOf(days));
    }

    // basic CRUD operations
    @Override
    public List<RentalDTO> getAllRentals() {
        return rentalRepository
                .findAll()
                .stream()
                .map(rentalMapper::toDTO)
                .toList();
    }

    @Override
    public Optional<RentalDTO> getRentalById(Long id) {
        return rentalRepository
                .findById(id)
                .map(rentalMapper::toDTO);
    }

    @Override
    @Transactional
    public RentalDTO createRental(RentalDTO rentalDTO) {
        // S1: validate car exists and if available or not
        Car car = carRepository .findById(rentalDTO.getCarId())
                                .orElseThrow(() -> new RuntimeException("Car not found with ID: " + rentalDTO.getCarId()));

        if(car.getStatus() != CarStatus.AVAILABLE) {
            throw new RuntimeException("Car status is not AVAILABLE for rental");
        }

        //S2: check for date conflicts
        if (!isCarAvailable(rentalDTO.getCarId(), rentalDTO.getStartDate(), rentalDTO.getEndDate())) {
            throw new RuntimeException("Car is already rented for the selected days");
        }

        //S3: validate customer exists
        Customer customer = customerRepository  .findById(rentalDTO.getCustomerId())
                                                .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + rentalDTO.getCustomerId()));

        //S4: calculate total cost automatically
        BigDecimal totalCost = calculatedRentalCost(car.getPricePerDay(),
                                                    rentalDTO.getStartDate(),
                                                    rentalDTO.getEndDate());

        //S5: Create rental entity
        Rental rental = rentalMapper.toEntity(rentalDTO);

        rental.setCar(car);
        rental.setCustomer(customer);
        rental.setTotalCost(totalCost);
        rental.setStatus(RentalStatus.ACTIVE);

        //S6: save rental
        Rental savedRental = rentalRepository.save(rental);

        //S7: update car status to RENTED
        car.setStatus(CarStatus.RENTED);
        carRepository.save(car);

        return rentalMapper.toDTO(savedRental);
    }

    @Override
    @Transactional
    public RentalDTO updateRental(Long id, RentalDTO rentalDTO) {
        // find existing rental
        Rental existingRental = rentalRepository
                                .findById(id)
                                .orElseThrow(() -> new RuntimeException("Rental not found with ID: " + id));

        //update fields (keeping existing car and customer)
        Rental updatedRental = new Rental();

        updatedRental.setId(id);
        updatedRental.setCar(existingRental.getCar());
        updatedRental.setCustomer(existingRental.getCustomer());
        updatedRental.setStartDate(rentalDTO.getStartDate());
        updatedRental.setEndDate(rentalDTO.getEndDate());
        updatedRental.setActualReturnDate(rentalDTO.getActualReturnDate());
        updatedRental.setTotalCost(rentalDTO.getTotalCost());
        updatedRental.setStatus(rentalDTO.getStatus());

        Rental  savedRental = rentalRepository.save(updatedRental);
        return rentalMapper.toDTO(savedRental);
    }

    @Override
    @Transactional
    public void deleteRental(Long id) {
        //check if rental exists
        Rental rental = rentalRepository
                        .findById(id)
                        .orElseThrow(() -> new RuntimeException("Rental not found with ID: " + id));

        //if rental is active, make car available again
        if (rental.getStatus() == RentalStatus.ACTIVE) {
            Car car = rental.getCar();
            car.setStatus(CarStatus.AVAILABLE);
            carRepository.save(car);
        }
        rentalRepository.deleteById(id);
    }


    // Filter/Search methods
    @Override
    public List<RentalDTO> getRentalByStatus(RentalStatus status) {
        return rentalRepository
                .findByStatus(status)
                .stream()
                .map(rentalMapper::toDTO)
                .toList();
    }

    @Override
    public List<RentalDTO> getRentalByCarId(Long carId) {
        return rentalRepository
                .findByCarId(carId)
                .stream()
                .map(rentalMapper::toDTO)
                .toList();
    }

    @Override
    public List<RentalDTO> getRentalByCustomerId(Long customerId) {
        return rentalRepository
                .findByCustomerId(customerId)
                .stream()
                .map(rentalMapper::toDTO)
                .toList();
    }

    @Override
    public List<RentalDTO> getOverDueRentals(){
        return rentalRepository
                .findOverDueRentals(LocalDate.now())
                .stream()
                .map(rentalMapper::toDTO)
                .toList();
    }


    // Business Logic methods

    @Override
    public boolean isCarAvailable(Long carId, LocalDate startDate, LocalDate endDate) {
        // get all active rentals for this car
        List<Rental> activeRentals = rentalRepository
                .findByCarIdAndCarStatus(carId, CarStatus.AVAILABLE);

        return activeRentals
                .stream()
                .noneMatch(rental -> datesOverlap(rental.getStartDate(), rental.getEndDate(), startDate, endDate));
    }

    @Override
    @Transactional
    public RentalDTO returnCar(Long rentalId) {
        // find rental
        Rental rental = rentalRepository
                        .findById(rentalId)
                        .orElseThrow(() -> new RuntimeException("Rental not found with ID: " + rentalId));

        if (rental.getStatus() != RentalStatus.ACTIVE){
            throw new RuntimeException("Rental is not active, cannot return car");
        }

        //set return date to today
        LocalDate returnDate = LocalDate.now();

        //calculate total costs
        BigDecimal finalCost = rental.getTotalCost();

        //update rental to completed
        rental.setActualReturnDate(returnDate);
        rental.setTotalCost(finalCost);
        rental.setStatus(RentalStatus.COMPLETED);

        // make car available again
        Car car = rental.getCar();
        car.setStatus(CarStatus.AVAILABLE);
        carRepository.save(car);

        Rental savedRental = rentalRepository.save(rental);
        return rentalMapper.toDTO(savedRental);
    }
}
