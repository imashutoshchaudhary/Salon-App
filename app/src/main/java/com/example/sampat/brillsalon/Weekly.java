package com.example.sampat.brillsalon;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Weekly extends AppCompatActivity {
    Toolbar toolbas;
    RecyclerView reec,reeec;
    TextView num;
    ArrayList<Productweeklyin> productList;
    ArrayList<Productweeklyexp> productweeklyexps;
    Adapterweekly adp;
    Adapterweeklyexp adpp;
    SharedPreferences sharedPreferences;
    private static final String URL_PRODUCTS="http://www.brilltechno.com/salon/android/monthlyincome.php";
    private static final String url="http://www.brilltechno.com/salon/android/monthlyexpances.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly);
        toolbas=findViewById(R.id.toolbas);
        setSupportActionBar(toolbas);
        reec=findViewById(R.id.reec);
        reeec=findViewById(R.id.reeec);
        num=findViewById(R.id.num);
        sharedPreferences =getSharedPreferences("prefUserDetail", MODE_PRIVATE);
        sharedPreferences.getString("id", "");
        productweeklyexps=new ArrayList<>();
        productList=new ArrayList<>();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Weekly Balance Sheet");
        reec.setLayoutManager(new LinearLayoutManager(this));
        reec.setHasFixedSize(true);
        reec.setLayoutManager(new LinearLayoutManager(this));
        adp=new Adapterweekly(this,productList);
        reec.setAdapter(adp);
        loadProducts();
        reeec.setLayoutManager(new LinearLayoutManager(this));
        adpp=new Adapterweeklyexp(this,productweeklyexps);
        reeec.setAdapter(adpp);
        loadProductss();

    }
    private void loadProducts() {
        String u1=URL_PRODUCTS+"?salon_id="+ sharedPreferences.getString("id", "");

        StringRequest stringRequest = new StringRequest(Request.Method.GET, u1,
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
                                productList.add(new Productweeklyin(


                                        product.getInt("userid"),

                                        product.getString("Date"),
                                         product.getString("Price")
                                      ));
                            }

                            //creating adapter object and setting it to recyclerview
                            Adapterweekly adapter = new Adapterweekly(Weekly.this, productList);
                            reec.setAdapter(adapter);
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
        Volley.newRequestQueue(this).add(stringRequest);
    }

   private void loadProductss() {
       String u=url+"?salon_id="+ sharedPreferences.getString("id", "");


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
                                productweeklyexps.add(new Productweeklyexp(



                                        product.getString("Prices")
                                ));
                            }

                            //creating adapter object and setting it to recyclerview
                            Adapterweeklyexp adapter1 = new Adapterweeklyexp(Weekly.this,productweeklyexps);
                            reeec.setAdapter(adapter1);
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
        Volley.newRequestQueue(this).add(stringRequest);
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
