package com.example.sampat.brillsalon;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PlatinumPlansFragment extends Fragment {
   /* TextView txtAboutUs ;
    SharedPreferences prefrance;

    ProgressBar progressBar;
    JsonArrayRequest jsonArrayReq;
    RequestQueue requestQueue;



    private AdapterGetPlatniumPlans recyclerAdapter;
    private ArrayList<GatterGetPlatinumplans> recyclerModels;

    List<GatterGetPlatinumplans> GetDataAdapter1;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager recyclerlayoutManager;

    private static final int LOAD_LIMIT = 15;
    private String lastId = "0";

    private boolean itShouldLoadMore = true;



    Boolean isinternetpresent;
    ConnectDetector cd;
    Dialog myDialog;
    String reslength="0";
    Button platinumPlan;
    GridView grid;
    int nextdata=0;*/
    public PlatinumPlansFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View  rootView= inflater.inflate(R.layout.fragment_platinumplans, container, false);
 /*       progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar1);
        myDialog = new Dialog(getContext());
        platinumPlan = rootView.findViewById(R.id.platinum_plan_info);
        recyclerModels = new ArrayList<>();
        recyclerAdapter = new AdapterGetPlatniumPlans(recyclerModels,getActivity());
        GetDataAdapter1 = new ArrayList<>();


        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.loadmore_recycler_view);

        recyclerlayoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(recyclerlayoutManager);
        recyclerView.setHasFixedSize(true);
        //we can now set adapter to recyclerView;


        recyclerView.setAdapter(recyclerAdapter);

        GetDataAdapter1 = new ArrayList<>();

        getStanderdPlans();

        platinumPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowPopup();
            }
        });*/

        return rootView;
    }

    /*private void getStanderdPlans() {
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setClickable(false);
        Log.i("","Configs.GetAllUser========"+ Configs.GetAllUser);
        //if everything is fine
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Configs.GetAllPlans,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // progressBar.setVisibility(View.GONE);
                        Log.i("","ggggggggggggggg"+response);
                        progressBar.setVisibility(View.GONE);
                        try {
                            JSONArray jsonArray = new JSONArray(response);

                            nextdata=jsonArray.length();
                            Log.i("","getAllUsersresponser============"+jsonArray.length());


                            for (int i = 0; i < jsonArray.length(); i++) {
                                try {

                                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                                    //  lastId= jsonObject.getString("id");

                                    //  Log.i("","ggggggggggggggg"+lastId);

                                    String planId ,planname,planduration,planprice,plannew;

                                    planId=jsonObject.getString("id");
                                    planname=jsonObject.getString("name");
                                    planduration=jsonObject.getString("duration");
                                    planprice=jsonObject.getString("price");
                                    plannew=jsonObject.getString("new");


                                    recyclerModels.add(new GatterGetPlatinumplans(planId,planname,planduration,planprice,plannew));
                                    recyclerAdapter.notifyDataSetChanged();
                                }
                                catch (JSONException e)
                                {

                                }

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }





                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toasty.error(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();

                        Log.i("","profile error========="+error.toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("GetPlatinum", "1");
                params.put("limit ",Integer.toString(nextdata) );

                Log.i("","next data profile error========="+nextdata);

                return params;
            }
        };

        VolleySingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);
    }
    public void ShowPopup() {
        TextView txtclose;
        myDialog.setContentView(R.layout.custompopup2);
        txtclose =(TextView) myDialog.findViewById(R.id.txtclose2);
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        myDialog.show();
    }*/
}
