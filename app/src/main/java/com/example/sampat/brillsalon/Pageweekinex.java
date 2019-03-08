package com.example.sampat.brillsalon;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class Pageweekinex extends FragmentPagerAdapter {
    private int l;
    public Pageweekinex(FragmentManager fm,int l) {

        super(fm);
        this.l=l;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return  new Incomebal();
            case 1:
                return  new Expensivebal();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return l;
    }
}
