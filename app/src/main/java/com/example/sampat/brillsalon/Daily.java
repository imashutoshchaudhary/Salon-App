package com.example.sampat.brillsalon;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
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

public class Daily extends AppCompatActivity {
    RecyclerView re,ree;
    Toolbar toolbs;
    TextView tt,to;
    Context cont;
    ArrayList<Productdailybal> productList;
    ArrayList<Productdialyexp> productdialyexps;
    Adapterdaily adp;
    Adapterdaily2 adpp;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily);
        productList=new ArrayList<>();
        productdialyexps=new ArrayList<>();
        re=findViewById(R.id.re);
        ree=findViewById(R.id.ree);
        toolbs=findViewById(R.id.toolbs);
        setSupportActionBar(toolbs);
        to=findViewById(R.id.to);
        tt=findViewById(R.id.tt);
        sharedPreferences =getSharedPreferences("prefUserDetail", MODE_PRIVATE);
        sharedPreferences.getString("id", "");

        tt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent b=new Intent(Daily.this,Message.class);
                startActivity(b);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Daily Balance Sheet");
        re.setHasFixedSize(true);

        re.setLayoutManager(new LinearLayoutManager(Daily.this));
        adp=new Adapterdaily(cont,productList);
        re.setAdapter(adp);
        loadProducts();
        ree.setHasFixedSize(true);
        ree.setLayoutManager(new LinearLayoutManager(this));
        adpp=new Adapterdaily2(cont,productdialyexps);
        ree.setAdapter(adpp);
        loadProductss();
        saveRequest();
    }
    private void loadProducts() {
        String URL_PRODUCTS="http://www.brilltechno.com/salon/android/dailyincome.php";
        String o=URL_PRODUCTS+"?salon_id="+    sharedPreferences.getString("id", "");


        StringRequest stringRequest = new StringRequest(Request.Method.GET, o,
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
                                productList.add(new Productdailybal(


                                        product.getInt("userid"),

                                        product.getString("Name"),
                                        product.getString("Service"),
                                        product.getString("Price"),
                                        product.getString("Date")
                                ));
                            }

                            //creating adapter object and setting it to recyclerview
                            Adapterdaily adapter = new Adapterdaily(Daily.this, productList);
                            re.setAdapter(adapter);
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
        Volley.newRequestQueue(Daily.this).add(stringRequest);
    }

    private void loadProductss() {
        String URL_PRODUCTSs="http://www.brilltechno.com/salon/android/dailyexpences.php";
        String o=URL_PRODUCTSs+"?salon_id="+    sharedPreferences.getString("id", "");

        StringRequest stringRequest = new StringRequest(Request.Method.GET, o,
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
                                productdialyexps.add(new Productdialyexp(


                                        product.getInt("userid"),

                                        product.getString("Name"),
                                        product.getString("Price"),
                                        product.getString("Date")
                                ));
                            }

                            //creating adapter object and setting it to recyclerview
                            Adapterdaily2 adapter1 = new Adapterdaily2(Daily.this, productdialyexps);
                            ree.setAdapter(adapter1);
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
        Volley.newRequestQueue(Daily.this).add(stringRequest);
    }

    private void saveRequest() {

        String url1 = "http://www.brilltechno.com/salon/android/dailybalance.php" ;
        String o=url1+"?salon_id="+    sharedPreferences.getString("id", "");

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
                        Toast.makeText(Daily.this, "Something went wrong", Toast.LENGTH_LONG).show();
                    }
                }) ;


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
        // NetworkCalls.getInstance().addToRequestQueue(request);


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case android.R.id.home:
                Intent g=new Intent(this,Balancesheet.class);
                startActivity(g);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
