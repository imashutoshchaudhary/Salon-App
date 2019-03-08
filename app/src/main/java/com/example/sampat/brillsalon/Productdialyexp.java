package com.example.sampat.brillsalon;

public class Productdialyexp {

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    int id;
    String service;
    String prize;
    String date;
    Productdialyexp(int id,String service,String prize,String date){
        this.date=date;
        this.id=id;
        this.service=service;
        this.prize=prize;
    }

}
