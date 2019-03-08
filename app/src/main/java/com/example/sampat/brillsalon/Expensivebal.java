package com.example.sampat.brillsalon;

import android.annotation.SuppressLint;
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
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
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
 * create an instance of this fragment.
 */
public class Expensivebal extends Fragment {
    ArrayList<Productdialyexp> productList;
    Adapterdaily2 adp;
    Context cont;
    RecyclerView re;
    TextView to;
    SharedPreferences sharedPreferences;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View p=inflater.inflate(R.layout.fragment_expensivebal, container, false);
        re=p.findViewById(R.id.re);
        productList=new ArrayList<>();
        re.setHasFixedSize(true);
        to=p.findViewById(R.id.to);
        sharedPreferences =getContext().getSharedPreferences("prefUserDetail", MODE_PRIVATE);
        sharedPreferences.getString("id", "");

        re.setLayoutManager(new LinearLayoutManager(getContext()));
        adp=new Adapterdaily2(cont,productList);
        re.setAdapter(adp);
        loadProductss();
         saveRequest();

        return p; }
    private void loadProductss() {
        String URL_PRODUCTSs="http://www.brilltechno.com/salon/android/weeklyexpances.php";
String u=URL_PRODUCTSs+"?salon_id="+sharedPreferences.getString("id", "");

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
                                productList.add(new Productdialyexp(


                                        product.getInt("userid"),

                                        product.getString("Name"),
                                        product.getString("Price"),
                                        product.getString("Date")
                                ));
                            }

                            //creating adapter object and setting it to recyclerview
                            Adapterdaily2 adapter1 = new Adapterdaily2(getContext(), productList);
                            re.setAdapter(adapter1);
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

    private void saveRequest() {

        String url1 = "http://www.brilltechno.com/salon/android/monthlybalance.php" ;
String o=url1+"?salon_id="+     sharedPreferences.getString("id", "");

        // Log.i("asdf", "url==========" + URL_SAVE);

        StringRequest request = new StringRequest(Request.Method.GET, o,
                new Response.Listener<String>() {
                    @SuppressLint("WrongConstant")
                    @Override
                    public void onResponse(String response)
                    {

                        Log.i("asdf", "api response=======" + response);
                        // Toast.makeText(Daily.this, response, Toast.LENGTH_LONG).show();
                        to.setText(response);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //  mDialog.dismiss();
                        Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_LONG).show();
                    }
                }) ;


        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(request);
        // NetworkCalls.getInstance().addToRequestQueue(request);


    }

   }
