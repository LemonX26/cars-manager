package com.car_manager.repository;

import com.car_manager.model.Car;
import com.car_manager.model.CarSaleAnnouncementDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CarSaleMongoRepository extends MongoRepository<CarSaleAnnouncementDocument, String> {
     List<CarSaleAnnouncementDocument> findByCar(Car car);
}
