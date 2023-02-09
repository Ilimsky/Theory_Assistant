package com.example.theoryassistant.activity;

public class Hint {
    public int id;
    public String name;
    public String price;
    public String details;

    public Hint(int id, String name, String price, String details) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.details = details;
    }

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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

}
