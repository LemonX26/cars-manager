package com.task_manager.model;


import jakarta.persistence.*;

@Entity
public class CarSaleAnnouncement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;
    private double price;

    public CarSaleAnnouncement() {
    }
    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
    public CarSaleAnnouncement(Car car, double price, String description, String contactInfo, String imageUrl, String location, String sellerName) {
        this.car = car;
        this.price = price;
        this.description = description;
        this.contactInfo = contactInfo;
        this.imageUrl = imageUrl;
        this.location = location;
        this.sellerName = sellerName;
    }

    private String description;
    private String contactInfo;
    private String imageUrl;
    private String location;
    private String sellerName;

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

    public boolean checkIfAllValuesAreSet() {
        return  car != null && price > 0 && description != null && !description.isBlank() &&
                contactInfo != null && !contactInfo.isBlank() && imageUrl != null && !imageUrl.isBlank() &&
                location != null && !location.isBlank() && sellerName != null && !sellerName.isBlank();

    }
}
