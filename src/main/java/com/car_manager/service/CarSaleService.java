package com.car_manager.service;

import com.car_manager.repository.CarRepository;
import com.car_manager.repository.CarSaleRepository;
import com.car_manager.model.CarSaleAnnouncement;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarSaleService extends CarService{
    private final CarRepository carRepository;
    private final CarSaleRepository carSaleRepository;

    public CarSaleService(CarRepository carRepository, CarSaleRepository carSaleRepository) {
        super(carRepository);
        this.carRepository = carRepository;
        this.carSaleRepository = carSaleRepository;
    }

    public void addCarSaleAnnouncement(CarSaleAnnouncement carSaleAnnouncement) {
        try {
            if(carSaleAnnouncement.checkIfAllValuesAreSet()){
                carRepository.save(carSaleAnnouncement.getCar());
                carSaleRepository.save(carSaleAnnouncement);
            }
            else {
                throw new IllegalArgumentException("Nie wszystkie wartości sa ustawione w ogłoszeniu.");
            }
        }catch (IllegalArgumentException e) {
            System.out.println("Nie można dodać ogłoszenia: " + e.getMessage());
        }

    }

    public List<CarSaleAnnouncement> findAll() {
        return carSaleRepository.findAll();
    }
}
