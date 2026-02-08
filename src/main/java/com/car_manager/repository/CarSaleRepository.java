package com.car_manager.repository;

import com.car_manager.model.Car;
import com.car_manager.model.CarSaleAnnouncement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarSaleRepository extends JpaRepository<CarSaleAnnouncement, Integer> {
    // Additional query methods can be defined here if needed
    // For example, to find announcements by car:
     List<CarSaleAnnouncement> findByCar(Car car);
}
