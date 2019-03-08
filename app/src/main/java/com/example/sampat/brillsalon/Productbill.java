package com.example.sampat.brillsalon;

public class Productbill {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    String name,number,birth,service,price;
    public Productbill(String name,String number,String birth,String service,String price){
        this.name=name;
        this.number=number;
        this.birth=birth;
        this.service=service;
        this.price=price;
    }
}
