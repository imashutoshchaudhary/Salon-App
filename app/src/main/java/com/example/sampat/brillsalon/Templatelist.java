package com.example.sampat.brillsalon;

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
public class Templatelist extends Fragment {
    private static final String URL_PRODUCTS="http://www.brilltechno.com/salon/android/msgrecycle.php";
    ArrayList<Producttemplate> temp;
    RecyclerView recc;
    SharedPreferences sharedPreferences;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        temp=new ArrayList<>();
        sharedPreferences =getContext().getSharedPreferences("prefUserDetail", MODE_PRIVATE);
        sharedPreferences.getString("id", "");
        View j= inflater.inflate(R.layout.fragment_templatelist, container, false);
        recc=j.findViewById(R.id.recc);
        recc.setHasFixedSize(true);
        recc.setLayoutManager(new LinearLayoutManager(getContext()));
        loadProducts();

        return j;
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
                                JSONObject product1 = array.getJSONObject(i);

                                //adding the product to product list
                                temp.add(new Producttemplate(
                                        product1.getInt("userid"),


                                        product1.getString("title"),
                                        product1.getString("message")
                                       ));
                            }

                            //creating adapter object and setting it to recyclerview
                            Adaptertemplate adapter = new Adaptertemplate(getContext(),temp );
                            recc.setAdapter(adapter);
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
