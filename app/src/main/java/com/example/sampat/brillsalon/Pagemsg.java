package com.example.sampat.brillsalon;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class Pagemsg extends FragmentPagerAdapter {
    private int k;
    public Pagemsg(FragmentManager fvm,int k) {
        super(fvm);
        this.k=k;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return  new Cretaetemp();
            case 1:
                return  new Templist();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return k;
    }
}
