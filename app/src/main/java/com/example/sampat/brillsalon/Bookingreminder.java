package com.example.sampat.brillsalon;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 */
public class Bookingreminder extends Fragment {
    RecyclerView re;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        // Inflate the layout for this fragment

        View p= inflater.inflate(R.layout.fragment_bookingreminder, container, false);
        re=p.findViewById(R.id.re);
        re.setHasFixedSize(true);
        String []n={"Sampat","Rahul","Sonu"};
        String []n1={"7877229253","9928442830","9460406510"};
        String []n2={"12/3/2018","9/9/2018","7/5/2018"};
        String []n3={"13:00","18:22","19:44"};
        String []n4={"4.5","3.4","4.9"};
        int []n5={R.drawable.sam,R.drawable.ra,R.drawable.c};
        re.setLayoutManager(new LinearLayoutManager(getContext()));
        re.setAdapter(new Adapterbooking(n,n1,n2,n3,n4,n5));

        re.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        re.setAdapter(new Adapterbooking(n,n1,n2,n3,n4,n5));



        return p;}

   }
