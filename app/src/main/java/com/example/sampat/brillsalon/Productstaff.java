package com.example.sampat.brillsalon;

public class Productstaff {

    int uid;
    String cnames,cphone,cemail,csalary,caddress;

    public Productstaff(int uid,String cname, String cphone, String cemail,String csalary,String caddress) {

        this.uid=uid;
        this.cnames = cname;
        this.cphone = cphone;
        this.cemail=cemail;
        this.csalary=csalary;
        this.caddress=caddress;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getCnames() {
        return cnames;
    }

    public void setCnames(String cname) {
        this.cnames = cname;
    }

    public String getCphone() {
        return cphone;
    }

    public void setCphone(String cphone) {
        this.cphone = cphone;
    }

    public String getCsalary() {
        return csalary;
    }

    public void setCsalary(String cemail) {
        this.csalary = csalary;
    }
    public String getCaddress() {
        return caddress;
    }

    public void setCaddress(String cemail) {
        this.caddress = caddress;
    }
    public String getCemail() {
        return cemail;
    }

    public void setCemail(String cemail) {
        this.cemail = cemail;
    }


}
