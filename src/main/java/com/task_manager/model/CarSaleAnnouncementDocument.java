package com.task_manager.model;

import com.task_manager.model.Car;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "carSaleAnnouncements")
public class CarSaleAnnouncementDocument {

    @Id
    private String id;  // String, aby Mongo mogło generować ObjectId

    private Car car;    // Zakładam, że Car jest zwykłą POJO bez JPA

    private double price;
    private String description;
    private String contactInfo;
    private String imageUrl;
    private String location;
    private String sellerName;

    public CarSaleAnnouncementDocument() {
    }

    public CarSaleAnnouncementDocument(Car car, double price, String description, String contactInfo, String imageUrl, String location, String sellerName) {
        this.car = car;
        this.price = price;
        this.description = description;
        this.contactInfo = contactInfo;
        this.imageUrl = imageUrl;
        this.location = location;
        this.sellerName = sellerName;
    }

    // Gettery i settery

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }
}
