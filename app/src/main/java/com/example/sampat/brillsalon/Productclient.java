package com.example.sampat.brillsalon;

/**
 * Created by JUNED on 6/16/2016.
 */
public class Productclient {

    int uid;
    String cname,cphone,cemail;

    public Productclient(int uid,String cname, String cphone, String cemail) {

        this.uid=uid;
        this.cname = cname;
        this.cphone = cphone;
        this.cemail=cemail;
       }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCphone() {
        return cphone;
    }

    public void setCphone(String cphone) {
        this.cphone = cphone;
    }

    public String getCemail() {
        return cemail;
    }

    public void setCemail(String cemail) {
        this.cemail = cemail;
    }

 }

/*public class Productclient{
        private String username;
        private String userphone;
        private String useremail;
        private int userid;




    public Productclient(String username, String userphone, String useremail, int userid) {
            this.username= username;
            this.userphone = userphone;
            this.useremail=useremail;
            this.userid=userid;

        }
    public String getUseremail() {
        return useremail;
    }
    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }


    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }


    public String getUserName() {
            return username;
        }
        public void setUserName(String username){


         this.username=username;
        }

        public String getUserPhone() {

            return userphone;
        }
        public void setUserPhone(String userphone){
            this.userphone=userphone;
        }

}*/
