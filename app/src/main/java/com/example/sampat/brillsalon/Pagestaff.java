package com.example.sampat.brillsalon;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class Pagestaff extends FragmentPagerAdapter {
   private int num;
    public Pagestaff(FragmentManager f,int num) {
        super(f);
        this.num=num;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return  new Addstaff();
            case 1:
                return  new Stafflist();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return num;
    }
}
