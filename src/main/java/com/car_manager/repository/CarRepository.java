package com.car_manager.repository;

import com.car_manager.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
@Repository

public interface CarRepository extends JpaRepository<Car, Integer>
{
    List<Car> findByNameContainingIgnoreCase(String name);
    List<Car> findByModelContainingIgnoreCase(String model);
    List<Car> findByCategoryContainingIgnoreCase(String category);
    List<Car> findByProductionYear(int year);
    List<Car> findByMileage(int mileage);
    List<Car> findByMileageBetween(int minMileage, int maxMileage);

    @Query("SELECT DISTINCT c.name FROM Car c")
    List<String> findAllNames();
    @Query("SELECT DISTINCT c.model FROM Car c")
    List<String> findAllModels();
    @Query("SELECT DISTINCT c.category FROM Car c")
    List<String> findAllCategories();
    @Query("SELECT DISTINCT c.productionYear FROM Car c")
    List<Integer> findAllProductionYears();
    @Query("SELECT DISTINCT c.mileage FROM Car c")
    List<Integer> findAllMileages();

}

