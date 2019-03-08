package com.example.sampat.brillsalon;

import android.os.Bundle;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class Printbill extends AppCompatActivity {
    TabLayout tab;
    TabItem itm,itm1;
    Pagebill pag;
    ViewPager view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_printbill);
        tab=findViewById(R.id.tab);
        itm=findViewById(R.id.itm);
        itm1=findViewById(R.id.itm1);
        view=findViewById(R.id.view);
        pag=new Pagebill(getSupportFragmentManager(),tab.getTabCount());
        view.setAdapter(pag);
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tabb) {
                view.setCurrentItem(tabb.getPosition());




            }

            @Override
            public void onTabUnselected(TabLayout.Tab tabb) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tabb) {

            }
        });
        view.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));
    }

}
