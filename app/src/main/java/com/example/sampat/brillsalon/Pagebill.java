package com.example.sampat.brillsalon;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class Pagebill extends FragmentPagerAdapter {
    private int f;
    public Pagebill(FragmentManager fom,int f) {
        super(fom);
        this.f=f;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return  new AddclFragment();
            case 1:
                return  new Existingclient();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return f;
    }
}
