package com.car_manager.model;
//JPA - okresla jak nazywaja sie adnotacje
// Hibernate - implementuje te adnotacje

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.car_manager.view.Views;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity //Informuje JPA, że klasa powinna być mapowana na tabelę w bazie danych.
@Table(name = "users") //Informuje JPA, że klasa powinna być mapowana na tabelę o nazwie "users".
public class User implements UserDetails
{
    @Id //Informuje JPA, że pole powinno być kluczem głównym w tabeli.
    @GeneratedValue(strategy =  GenerationType.IDENTITY) //Informuje JPA, że wartość tego pola powinna być generowana automatycznie.
    @JsonIgnore
    private int id;

    @Column(unique = true) //Informuje JPA, że wartość tego pola powinna być unikalna.
    @JsonView(Views.Basic.class) //
    private String username;

    @JsonIgnore //Informuje JPA, że wartość tego pola powinna być ignorowana podczas serializacji do JSON.
    private String password;
    @JsonView(Views.Detailed.class)
    private String email;

    @OneToMany
    @JsonBackReference
    private List<Car> cars = new ArrayList<>();; //Relacja jeden do wielu z klasą Car

    @Enumerated(EnumType.STRING) //Informuje JPA, że wartość tego pola powinna być zapisywana jako łańcuch znaków.
    private Role role;
    public User() {
    }

    public User(int id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;

    }

    public User(String username, String password, String email, Role role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
        //ROLA/POZWOLENIE
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public List<Car> getCars() {
        return cars;
    }

    public void addCar(Car car) {
        this.cars.add(car);
        car.setUser(this); // add car to user
    }

    public void addCars(List<Car> car) {
        this.cars.addAll(car); // add all cars of one list to another
        this.cars.forEach(c -> c.setUser(this));
    }
    // SQL - CREATE TABLE
    // HIBERNATE
    // MVC Model View Controller
}

