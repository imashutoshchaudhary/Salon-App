package com.example.sampat.brillsalon;

public class Productmsgsend {

        //private boolean isSelected;
        String ename,mobile,chec;

        public Productmsgsend(String ename,  String mobile) {
            this.ename = ename;
            this.mobile = mobile;
            this.chec = chec;
        }

        public Productmsgsend(int userid, String username, String userphone, String chec) {
            this.chec = chec;
        }

        public String getChec() {
            return chec;
        }

        public String setChec(String chec) {
            this.chec = chec;
            return chec;
        }

        public String getEname() {
            return ename;
        }

        public void setEname(String ename) {
            this.ename = ename;
        }


        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }


    }


