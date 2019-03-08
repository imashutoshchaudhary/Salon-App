package com.example.sampat.brillsalon;

public class Productweeklyin {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    int id;
    String date;
    String price;
Productweeklyin(int id,String date,String price){
        this.id=id;
        this.date=date;
        this.price=price;

    }
}