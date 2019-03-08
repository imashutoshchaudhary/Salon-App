package com.example.sampat.brillsalon;

import android.os.Bundle;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class Reminder extends AppCompatActivity {
    ViewPager view1;
    TabLayout tab1;
    TabItem itm,itm1,itm2,itm3;
    Pagereminder page1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);
        view1=findViewById(R.id.view1);
        tab1=findViewById(R.id.tab1);
        itm=findViewById(R.id.itm);
        itm1=findViewById(R.id.itm1);
        itm2=findViewById(R.id.itm2);
        itm3=findViewById(R.id.itm3);
        page1=new Pagereminder(getSupportFragmentManager(),tab1.getTabCount());
        view1.setAdapter(page1);
        tab1.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tabb) {
                view1.setCurrentItem(tabb.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tabb) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tabb) {

            }
        });
        view1.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab1));
        class OOM{
            static final int SIZE=2*1024*1024;
            public  void main(String[] a){
                int i[]=new int[SIZE];
            }
        }

    }

  }

