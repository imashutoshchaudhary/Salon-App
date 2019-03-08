package com.example.sampat.brillsalon;

public class Producttemplate {

    int uid;


    String message,title;

    Producttemplate(int uid,String title,String message){
        this.uid=uid;
        this.title=title;
        this.message=message;
    }
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public void setTitle(String title) {
        this.title = title;
    }



}
