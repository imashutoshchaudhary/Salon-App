package com.example.sampat.brillsalon;

import android.content.Context;
import android.net.Uri;
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
public class Templist extends Fragment {
    RecyclerView rece;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        View view=inflater.inflate(R.layout.fragment_templist, container, false);
        rece=view.findViewById(R.id.rece);
        rece.setLayoutManager(new LinearLayoutManager(getContext()));
        String sa[]={"Birthday","Exam","Anniversry"};

        String n[]={"Happy birthday","Good luck","Happy anniversry"};
        rece.setAdapter(new Adaptertemp(sa,n));
        rece.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        rece.setAdapter(new Adaptertemp(sa,n));

   return view; }


}
