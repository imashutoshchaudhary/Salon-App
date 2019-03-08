package com.example.sampat.brillsalon;

public class Productservice {


    int id;
String service,prize;
Productservice(int id,String service,String prize){
    this.id=id;
    this.service=service;
    this.prize=prize;
}
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }
}
