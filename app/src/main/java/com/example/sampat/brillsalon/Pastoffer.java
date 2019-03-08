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

 * create an instance of this fragment.
 */
public class Pastoffer extends Fragment {
    RecyclerView rex;
    Context cont;
    ArrayList<Productoffer> productList;
    Adapteroffer adp;
    SharedPreferences sharedPreferences;
    private static final String URL_PRODUCTS="http://www.brilltechno.com/salon/android/offer_list.php";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        View t= inflater.inflate(R.layout.fragment_pastoffer, container, false);
        rex = t.findViewById(R.id.rex);
        productList=new ArrayList<>();
        //  edit=view.findViewById(R.id.edit);
        //delete=view.findViewById(R.id.delete);
        rex.setHasFixedSize(true);
        sharedPreferences =getContext().getSharedPreferences("prefUserDetail", MODE_PRIVATE);
        sharedPreferences.getString("id", "");

        rex.setLayoutManager(new LinearLayoutManager(getContext()));
        adp=new Adapteroffer(cont,productList);
        rex.setAdapter(adp);
        loadProducts();

        return t;

    }
    private void filters(String s) {

        ArrayList<Productoffer> newList=new ArrayList<>();

        for (Productoffer dataclient : productList) {
            String name = dataclient.getOname().toLowerCase();
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
                                productList.add(new Productoffer(


                                        product.getInt("userid"),

                                        product.getString("Name"),
                                        product.getString("Amount"),
                                        product.getString("coupon_code")
                                ));
                            }

                            //creating adapter object and setting it to recyclerview
                            Adapteroffer adapter = new Adapteroffer(getContext(), productList);
                            rex.setAdapter(adapter);
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
