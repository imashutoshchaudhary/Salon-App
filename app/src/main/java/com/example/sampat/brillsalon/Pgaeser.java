package com.example.sampat.brillsalon;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class Pgaeser extends FragmentPagerAdapter {
    private  int s;
    public Pgaeser(FragmentManager fmm,int s) {

        super(fmm);
        this.s=s;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return  new Addser();
            case 1:
                return  new Servicelist();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return s;
    }
}
