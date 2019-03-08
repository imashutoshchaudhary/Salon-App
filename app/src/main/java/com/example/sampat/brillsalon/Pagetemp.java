package com.example.sampat.brillsalon;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class Pagetemp extends FragmentPagerAdapter {
    private  int numofTabs;
    Pagetemp(FragmentManager fm , int numofTabs) {
        super(fm);
        this.numofTabs=numofTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return  new Addtemplate();
            case 1:
                return  new Templatelist();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return numofTabs;
    }
}
