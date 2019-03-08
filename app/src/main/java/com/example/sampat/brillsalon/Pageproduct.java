package com.example.sampat.brillsalon;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class Pageproduct extends FragmentPagerAdapter {
    private int d;
    public Pageproduct(FragmentManager fmo,int d) {
        super(fmo);
        this.d=d;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return  new Addsoldpro();
            case 1:
                return  new Productlist();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return d;
    }
}
