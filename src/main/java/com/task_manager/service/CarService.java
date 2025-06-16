package com.task_manager.service;

import com.task_manager.model.Car;
import com.task_manager.model.CarStatus;
import com.task_manager.repository.CarRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;
import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CarService {
    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> getCars()
    {
        return carRepository.findAll();
    }

    public String getCarByName(String name) throws UsernameNotFoundException
    {
        return carRepository.findByNameContainingIgnoreCase(name).toString();
    }
    public String getCarByModel(String model){
        return carRepository.findByModelContainingIgnoreCase(model).toString();
    }

    public void addCar(Car car) {
        carRepository.save(car);
    }

    public void deleteCar(int carId) {
        Car car = carRepository.findById(carId).orElseThrow(() -> new IllegalArgumentException("Car not found"));
        carRepository.delete(car);
    }

    public List<Car> filterCars(String name, String model, String category, Integer productionYear, Integer minMileage, Integer maxMileage, String status) {
        return carRepository.findAll()
                .stream()
                .filter(car -> (name == null || name.isBlank() || car.getName().equalsIgnoreCase(name)))
                .filter(car -> (model == null || model.isBlank() || car.getModel().equalsIgnoreCase(model)))
                .filter(car -> (category == null || category.isBlank() || car.getCategory().equalsIgnoreCase(category)))
                .filter(car -> (productionYear == null || car.getProductionYear() == productionYear))
                .filter(car -> (minMileage == null || car.getMileage() >= minMileage))
                .filter(car -> (maxMileage == null || car.getMileage() <= maxMileage))
                .filter(car -> status == null ||  car.getStatus().toString().equalsIgnoreCase(status))
                .toList();

    }
    public List<String> findModelsForCarName(String name) {
        return carRepository.findAll()
                .stream()
                .filter(car -> car.getName().equalsIgnoreCase(name))
                .map(Car::getModel)
                .distinct()
                .toList();
    }

    public List<String> getALlNames() {
        return carRepository.findAll()
                .stream()
                .map(Car::getName)
                .distinct()
                .toList();
    }
    public List<String> getAllModels() {
        return carRepository.findAll()
                .stream()
                .map(Car::getModel)
                .distinct()
                .toList();
    }
    public List<String> getAllCategories() {
        return carRepository.findAll()
                .stream()
                .map(Car::getCategory)
                .distinct()
                .toList();
    }
    public List<Integer> getAllProductionYears() {
        return carRepository.findAll()
                .stream()
                .map(Car::getProductionYear)
                .toList();
    }
    public List<Integer> getAllMileages() {
        return carRepository.findAll()
                .stream()
                .map(Car::getMileage)
                .toList();
    }

    public Car getAllCarData(int Id){
        return carRepository.findById(Id)
                .orElseThrow(() -> new IllegalArgumentException("Car not found"));
    }

    public List<String> getAvailableValuesForAttribute(String attribute) {
        return switch (attribute) {
            case "name" -> carRepository.findAllNames();
            case "model" -> carRepository.findAllModels();
            case "category" -> carRepository.findAllCategories();
            case "productionYear" -> carRepository.findAllProductionYears().stream()
                    .map(String::valueOf).toList();
            case "mileage" -> carRepository.findAllMileages().stream()
                    .map(String::valueOf).toList();
            default -> throw new IllegalArgumentException("Invalid attribute: " + attribute);
        };
    }
    public List<Car> findByMileageBetween(int minMileage, int maxMileage) {
        return carRepository.findByMileageBetween(minMileage, maxMileage);
    }

    public List<Car> sortBy(String sortValue) {
        Comparator<Car> comparator = switch(sortValue){
            case "name" -> Comparator.comparing(Car::getName);
            case "model" -> Comparator.comparing(Car::getModel);
            case "category" -> Comparator.comparing(Car::getCategory);
            case "productionYear" -> Comparator.comparing(Car::getProductionYear);
            case "mileage" -> Comparator.comparing(Car::getMileage);
            default -> throw new IllegalStateException("Unexpected value: " + sortValue);
        };

        return carRepository.findAll()
                .stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }
    public List<CarStatus> getAllCarStatus() {
        return carRepository.findAll().stream().map(Car::getStatus)
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());
    }



}
