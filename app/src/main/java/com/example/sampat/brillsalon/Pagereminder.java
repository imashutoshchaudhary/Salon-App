package com.example.sampat.brillsalon;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class Pagereminder  extends FragmentPagerAdapter {
    private int lo;
    public Pagereminder(FragmentManager fm, int lo) {

        super(fm);
        this.lo=lo;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return  new Birthdayreminder();
            case 1:
                return  new Bookingreminder();
            case 2:
                return  new Productreminder();
            case 3:
                return  new Otherreminder();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return lo;
    }
}

