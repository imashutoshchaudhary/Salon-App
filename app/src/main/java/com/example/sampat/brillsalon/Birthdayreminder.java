package com.example.sampat.brillsalon;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

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
public class Birthdayreminder extends Fragment {
    Button a;
    RecyclerView list;
    EditText estview;
    Context cont;
    RequestQueue requestQueue;
SharedPreferences sharedPreferences;
    ArrayList<Productmsgsend> productList;
    Adaptersend adp;
    private static final String URL_PRODUCTS="http://www.brilltechno.com/salon/android/emailrecycle.php";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        final View p=inflater.inflate(R.layout.fragment_birthdayreminder, container, false);
        productList=new ArrayList<>();
        estview=p.findViewById(R.id.estview);
        a=p.findViewById(R.id.a);
         list=p.findViewById(R.id.list);
        list.setHasFixedSize(true);
        sharedPreferences =getContext().getSharedPreferences("prefUserDetail", MODE_PRIVATE);
        sharedPreferences.getString("id", "");

        list.setLayoutManager(new LinearLayoutManager(getContext()));
        adp=new Adaptersend(cont,productList);
        list.setAdapter(adp);

        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent g=new Intent(getContext(),Sendmessage.class);
                String[] li;

                ArrayList<Productmsgsend> newList=new ArrayList<>();
                int l = 0;


                ArrayList<String> array = new ArrayList<String>();

                for (Productmsgsend dataEmail : productList) {
                    String name = dataEmail.getChec();

                        if(!dataEmail.getMobile().equals("")) {
                            array.add(dataEmail.getMobile());
                        }

                    else
                    {
                        String mo= dataEmail.chec;
                        Log.i("kdgldkf","checked 0 no============"+mo);
                    }
                }

                Log.i("kdgldkf","array length=="+array.size());

                // i.putExtra("name",array);
                g.putExtra("mobile", array);
                startActivity(g);

            }


        });

        estview.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filters(estview.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        loadProducts();
        return p;

    }

    private void filters(String s) {

        ArrayList<Productmsgsend> newList=new ArrayList<>();

        for (Productmsgsend dataEmail : productList) {
            String name = dataEmail.getEname().toLowerCase();
            if (name.startsWith(s))
            {
                newList.add(dataEmail);

            }

        }
        adp.setFilter(newList);


    }



    private void loadProducts() {
        String o=URL_PRODUCTS+"?salon_id="+    sharedPreferences.getString("id", "");

            StringRequest stringRequest66=new StringRequest(Request.Method.GET, o, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);


                        Log.i("kdgldkflkgdf","emailresponce============"+response);


                        for (int i = 0; i < jsonArray.length(); i++)

                        {

                            try {

                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                //  lastId= jsonObject.getString("id");

                                //  Log.i("","ggggggggggggggg"+lastId);

                                String  ename,ephone;

                                //  boolean chk;

                                //  chk=jsonObject.getBoolean("");


                                ename=jsonObject.getString("ename");
                                ephone=jsonObject.getString("eph");
                                Log.i("ksdfdsffffff","jsom name============"+ename);
                                Log.i("ksdfdsffffff","jsom name============"+ephone);

                                String checked="0";


                                productList.add(new Productmsgsend(ename,ephone));
                                adp.notifyDataSetChanged();
                            }
                            catch (JSONException e)
                            {
                                e.printStackTrace();
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

                        }
                    });
            requestQueue= Volley.newRequestQueue(getContext());
            requestQueue.add(stringRequest66);
        }



    }
