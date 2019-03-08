package com.example.sampat.brillsalon;

import android.annotation.SuppressLint;
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

public class Monthly extends AppCompatActivity {
    Toolbar toolbaas;
    RecyclerView reecc,reeecc;
    private static final String URL_PRODUCTS="http://www.brilltechno.com/salon/android/monthlyincome.php";
    private static final String URL_PRODUCTSS="http://www.brilltechno.com/salon/android/monthlyexpances.php";

    ArrayList<Productmonthbal> productList;
    Adaptermonth adp;
    ArrayList<Productmonth2> productmonth2s;
    Adaptermonth2 a;
    TextView to;
    SharedPreferences sharedPreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly);
        reecc=findViewById(R.id.reecc);
        toolbaas=findViewById(R.id.toolbaas);
        reeecc=findViewById(R.id.reeecc);
        setSupportActionBar(toolbaas);
        to=findViewById(R.id.to);


        productList=new ArrayList<>();
        productmonth2s=new ArrayList<>();
        sharedPreferences =getSharedPreferences("prefUserDetail", MODE_PRIVATE);
        sharedPreferences.getString("id", "");

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Monthly Balance Sheet");
        reecc.setLayoutManager(new LinearLayoutManager(this));
        reecc.setHasFixedSize(true);
        reecc.setLayoutManager(new LinearLayoutManager(this));
        adp=new Adaptermonth(this,productList);
        reecc.setAdapter(adp);
        loadProducts();
        reeecc.setLayoutManager(new LinearLayoutManager(this));
        a=new Adaptermonth2(this,productmonth2s);
        reeecc.setAdapter(a);
        loadProductss();
       saveRequest();
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
                                productList.add(new Productmonthbal(


                                        product.getInt("userid"),

                                        product.getString("Price"),
                                        product.getString("Date")

                                ));
                            }

                            //creating adapter object and setting it to recyclerview
                            Adaptermonth adapter = new Adaptermonth(Monthly.this, productList);
                            reecc.setAdapter(adapter);
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
        String u2=URL_PRODUCTSS+"?salon_id="+ sharedPreferences.getString("id", "");


        StringRequest stringRequest = new StringRequest(Request.Method.GET, u2,
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
                                productmonth2s.add(new Productmonth2(


                                        product.getString("Prices")

                                ));
                            }

                            //creating adapter object and setting it to recyclerview
                            Adaptermonth2 adapter = new Adaptermonth2(Monthly.this, productmonth2s);
                            reeecc.setAdapter(adapter);
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

    private void saveRequest() {


        String url1 = "http://www.brilltechno.com/salon/android/monthlybalance.php" ;
        String u1=url1+"?salon_id="+ sharedPreferences.getString("id", "");


        // Log.i("asdf", "url==========" + URL_SAVE);

        StringRequest request = new StringRequest(Request.Method.GET, u1,
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
                        Toast.makeText(Monthly.this, "Something went wrong", Toast.LENGTH_LONG).show();
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

