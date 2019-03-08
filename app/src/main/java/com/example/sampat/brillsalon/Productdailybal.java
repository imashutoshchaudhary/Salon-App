package com.example.sampat.brillsalon;

public class Productdailybal {
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

    int id;
    String name;
    String service;
    String prize;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    String date;
    Productdailybal(int id,String name,String service,String prize,String date){
        this.date=date;
        this.id=id;
        this.name=name;
        this.service=service;
        this.prize=prize;
    }
}
