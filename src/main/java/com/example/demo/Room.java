package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Room {
    @Id
    @GeneratedValue
    private long id;
    private String city;
    private String states;
    private long price;

    public String getIsRentednow() {
        return isRentednow;
    }

    public void setIsRentednow(String isRentednow) {
        this.isRentednow = isRentednow;
    }

    private String isRentednow;


    public boolean isRented() {
        return rented;
    }

    public void setRented(boolean rented) {
        rented = rented;
    }

    private boolean rented;

    private String address;

    public long getId() {
        return id;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states;
    }




}
