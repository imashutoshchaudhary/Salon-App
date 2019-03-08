package com.example.sampat.brillsalon;

public class Productmonthbal {
    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }


    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    int id;
    String income;
    String month;


    Productmonthbal(int id,String income,String month){
        this.id=id;
        this.income=income;
        this.month=month;
    }
}
