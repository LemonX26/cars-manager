package com.task_manager.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Entity
public class Car
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;
    private String model;
    private String category;
    private int productionYear;
    private int mileage;

    private String originCountry;

    private CarStatus status;

    // RELACJA: Many Cars can belong to One User
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")

    private User user;

    public Car() {
        // Hibernate wymaga pustego konstruktora
    }

    public Car(String name, String model, String category, int year, int mileage, String originCountry, CarStatus status) {
        this.name = name;
        this.model = model;
        this.category = category;
        this.productionYear = year;
        this.mileage = mileage;
        this.originCountry = originCountry;
        this.status = status;
    }

    // GETTERY I SETTERY

    public int getId() {
            return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    public CarStatus getStatus() {
        return status;
    }

    public void setStatus(CarStatus status) {
        this.status = status;
    }

}
