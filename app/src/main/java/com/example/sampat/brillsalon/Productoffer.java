package com.example.sampat.brillsalon;

public class Productoffer
{
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOname() {
        return oname;
    }

    public void setOname(String oname) {
        this.oname = oname;
    }

    public String getOamont() {
        return oamont;
    }

    public void setOamont(String oamont) {
        this.oamont = oamont;
    }

    public String getOcoupn() {
        return ocoupn;
    }

    public void setOcoupn(String ocoupn) {
        this.ocoupn = ocoupn;
    }

    int id;
String oname,oamont,ocoupn;
Productoffer(int id,String oname,String oamont,String ocoupn){
    this.id=id;
    this.oname=oname;
    this.oamont=oamont;
    this.ocoupn=ocoupn;
}

}
