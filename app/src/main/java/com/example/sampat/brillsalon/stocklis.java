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
public class stocklis extends Fragment {
    RecyclerView recycl;
    Context cont;
    ArrayList<Productstock> productList;
    Adapterstock adp;
    SharedPreferences sharedPreferences;
    private static final String URL_PRODUCTS="http://www.brilltechno.com/salon/android/stockrecycle.php";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        // Inflate the layout for this fragment
        View dp= inflater.inflate(R.layout.fragment_stocklis, container, false);
        recycl=dp.findViewById(R.id.recycl);
        productList=new ArrayList<>();
        sharedPreferences =getContext().getSharedPreferences("prefUserDetail", MODE_PRIVATE);
        sharedPreferences.getString("id", "");
        recycl.setHasFixedSize(true);
        recycl.setLayoutManager(new LinearLayoutManager(getContext()));
        adp=new Adapterstock(cont,productList);
        recycl.setAdapter(adp);
        loadProducts();

        return dp;}
    private void filters(String s) {

        ArrayList<Productstock> newList=new ArrayList<>();

        for (Productstock dataclient : productList) {
            String name = dataclient.getBname().toLowerCase();
            if (name.startsWith(s))
            {
                newList.add(dataclient);

            }

        }
        adp.Filter(newList);


    }
    private void loadProducts() {
        String u=URL_PRODUCTS+"?salon_id="+ sharedPreferences.getString("id", "");

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
                                productList.add(new Productstock(
                                        product.getInt("id"),

                                        product.getString("brandname"),
                                        product.getString("productname"),
                                        product.getString("quantity"),
                                        product.getString("stockprice")
                                ));
                            }

                            //creating adapter object and setting it to recyclerview
                            Adapterstock adapter = new Adapterstock(getContext(), productList);
                            recycl.setAdapter(adapter);
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
