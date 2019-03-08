package com.example.sampat.brillsalon;

public class Productstock {
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getQun() {
        return qun;
    }

    public void setQun(String qun) {
        this.qun = qun;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    int uid;
    String bname,pname,qun,prize;

    public Productstock(int uid,String bname, String pname, String qun,String prize) {

        this.uid=uid;
        this.bname = bname;
        this.pname = pname;
        this.qun=qun;
        this.prize=prize;
    }


}
