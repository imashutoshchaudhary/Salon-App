package com.example.sampat.brillsalon;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class Pageoffer extends FragmentPagerAdapter {
    private int lc;
    public Pageoffer(FragmentManager fmp,int lc) {
        super(fmp);
        this.lc=lc;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return  new Addoffer();
            case 1:
                return  new Pastoffer();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return lc;
    }
}
