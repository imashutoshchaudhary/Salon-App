package com.example.sampat.brillsalon;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the

 */
public class Stafflist extends Fragment {
    RecyclerView rec;
    Context cont;
    ArrayList<Productstaff> productList;
    SharedPreferences sharedPreferences;
    Adapterstf adp;
    private static final String URL_PRODUCTS="http://www.brilltechno.com/salon/android/staffrecycle.php";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        // Inflate the layout for this fragment
        View ve= inflater.inflate(R.layout.fragment_stafflist, container, false);
        productList=new ArrayList<>();
        rec = ve.findViewById(R.id.recyc);
        rec.setHasFixedSize(true);
        sharedPreferences =getContext().getSharedPreferences("prefUserDetail", MODE_PRIVATE);
        sharedPreferences.getString("id", "");


        rec.setLayoutManager(new LinearLayoutManager(getContext()));
        adp=new Adapterstf(cont,productList);
        rec.setAdapter(adp);
        loadProducts();

        return  ve;}
    private void filters(String s) {

        ArrayList<Productstaff> newList=new ArrayList<>();

        for (Productstaff data : productList) {
            String name = data.getCnames().toLowerCase();
            if (name.startsWith(s))
            {
                newList.add(data);

            }

        }
        adp.Filter(newList);


    }
    private void loadProducts() {
        String u=URL_PRODUCTS+"?salon_id="+ sharedPreferences.getString("id", "");


        /*
         * Creating a String Request
         * The request type is GET defined by first parameter
         * The URL is defined in the second parameter
         * Then we have a Response Listener and a Error Listener
         * In response listener we will get the JSON response as a String
         * */
        StringRequest stringRequest = new StringRequest(Request.Method.GET, u,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Log.i("as","api========="+response);
                        try {
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);

                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject product = array.getJSONObject(i);

                                //adding the product to product list
                                productList.add(new Productstaff(


                                        product.getInt("userid"),

                                        product.getString("stfname"),
                                        product.getString("stfphone"),
                                        product.getString("stfemail"),
                                        product.getString("stfsalary"),
                                        product.getString("stfaddrs")
                                ));
                            }

                            //creating adapter object and setting it to recyclerview
                            Adapterstf adapter = new Adapterstf(getContext(), productList);
                            rec.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //adding our stringrequest to queue
        Volley.newRequestQueue(getContext()).add(stringRequest);
    }






}
