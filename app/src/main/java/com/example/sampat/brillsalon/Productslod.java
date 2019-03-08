package com.example.sampat.brillsalon;

public class Productslod {
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

    int id;
    String name,price;
    Productslod(int id,String name,String price){
        this.id=id;
        this.name=name;
        this.price=price;
    }

}
