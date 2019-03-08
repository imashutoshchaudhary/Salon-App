package com.example.sampat.brillsalon;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class Pagestock extends FragmentPagerAdapter {
    private int l;
    public Pagestock(FragmentManager fm,int l) {

        super(fm);
        this.l=l;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return  new Addstock();
            case 1:
                return  new stocklis();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return l;
    }
}
