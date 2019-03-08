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

 * create an instance of this fragment.
 */
public class ClientlsFragment extends Fragment  {
    RecyclerView recycle;
   // TextView edit,delete;
    Context cont;
    ArrayList<Productclient> productList;
    SharedPreferences sharedPreferences;
      Adaapter adp;
    private static final String URL_PRODUCTS="http://www.brilltechno.com/salon/android/clientrecycle.php";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_clientls,container,false);
       productList=new ArrayList<>();
        recycle = view.findViewById(R.id.recycle);
        sharedPreferences =getContext().getSharedPreferences("prefUserDetail", MODE_PRIVATE);
        sharedPreferences.getString("id", "");

        //  edit=view.findViewById(R.id.edit);
        //delete=view.findViewById(R.id.delete);
        recycle.setHasFixedSize(true);

        recycle.setLayoutManager(new LinearLayoutManager(getContext()));
        adp=new Adaapter(cont,productList);
        recycle.setAdapter(adp);
        loadProducts();

       return view;

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
                                productList.add(new Productclient(


                                        product.getInt("userid"),

                                        product.getString("username"),
                                        product.getString("userphone"),
                                        product.getString("useremail")
                                        ));
                            }

                            //creating adapter object and setting it to recyclerview
                            Adaapter adapter = new Adaapter(getContext(), productList);
                            recycle.setAdapter(adapter);
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
