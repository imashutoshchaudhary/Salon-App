package com.example.sampat.brillsalon;

/**
 * Created by brill on 2/6/2018.
 */

public class GatterGetPlatinumplans {


    private String planId ,planname,planduration,planprice,plannew;

    public GatterGetPlatinumplans(String planId, String planname, String planduration, String planprice, String plannew) {
        this.planId = planId;
        this.planname = planname;
        this.planduration = planduration;
        this.planprice = planprice;
        this.plannew = plannew;
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getPlanname() {
        return planname;
    }

    public void setPlanname(String planname) {
        this.planname = planname;
    }

    public String getPlanduration() {
        return planduration;
    }

    public void setPlanduration(String planduration) {
        this.planduration = planduration;
    }

    public String getPlanprice() {
        return planprice;
    }

    public void setPlanprice(String planprice) {
        this.planprice = planprice;
    }

    public String getPlannew() {
        return plannew;
    }

    public void setPlannew(String plannew) {
        this.plannew = plannew;
    }
}
