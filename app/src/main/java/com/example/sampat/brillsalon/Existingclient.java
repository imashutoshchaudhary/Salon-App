package com.example.sampat.brillsalon;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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
public class Existingclient extends Fragment {
    Spinner spi;
    Productbill pb;
    Button add,adds,addbill;
    TextView bill,getservice,getprice,total;
    EditText clnumber;
    TextView clname,Birthday;
    ArrayList<String> servicelits;
    String userphone,userid,cn,cb,ser,pr,cn1;
    RequestQueue requestQueue;
    String s,pri;
   public static  String name,price,n,p,nu,tot;
   RecyclerView rebill;
   RecyclerView.Adapter adp;
  ArrayList<Productserviceprice> pro;
   Context cont;
   SharedPreferences sharedPreferences;
    private static final String url= "http://brilltechno.com//salon/android/selectprice.php";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        // Inflate the layout for this fragment
        View c= inflater.inflate(R.layout.fragment_existingclient, container, false);
        spi=c.findViewById(R.id.spi);
        pro=new ArrayList<>();
        sharedPreferences =getContext().getSharedPreferences("prefUserDetail", MODE_PRIVATE);
        sharedPreferences.getString("id", "");
        rebill=c.findViewById(R.id.rebill);
        total=c.findViewById(R.id.total);
        rebill.setHasFixedSize(true);
        addbill=c.findViewById(R.id.addbill);
        getservice=c.findViewById(R.id.getservice);
        getprice=c.findViewById(R.id.getprice);
        clnumber=c.findViewById(R.id.clnumber);
        adds=c.findViewById(R.id.adds);
        clname=c.findViewById(R.id.clname);
        Birthday=c.findViewById(R.id.Birthday);
        add=c.findViewById(R.id.add);
        rebill.setLayoutManager(new LinearLayoutManager(getContext()));
       adp=new Adapterbill(cont,pro);
        rebill.setAdapter(adp);
        addbill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name=spi.getSelectedItem().toString();
                price=getprice.getText().toString();

                pro.add(new Productserviceprice(name,price));
                adp.notifyDataSetChanged();

                int t=0;


                for(int i=0;i<=pro.size()-1;i++)
                {
                    Log.i("single price","single value price"+pro.get(i).s_price);

                    t =t+Integer.parseInt(pro.get(i).s_price);

                }
                Log.i("tohhhh","total price"+t);



                total.setText(Integer.toString(t));
                 tot=total.getText().toString().trim();
            }
        });

        adds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveRequest();
              name=spi.getSelectedItem().toString();
              price=getprice.getText().toString();

                Intent p=new Intent(getContext(),Showbill.class);
                String uid= String.valueOf(userid);

                p.putExtra("name",cn1);
                p.putExtra("birthday",cb);
                p.putExtra("number",userphone);
                p.putExtra("service",ser);
                p.putExtra("price",pr);
                p.putExtra("id",uid);
                startActivity(p);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cn=clnumber.getText().toString().trim();
                if(cn.isEmpty()||cn.length()<10||cn.length()>10)
                {
                    Toast.makeText(getContext(),"Enter Correct Client Number ",Toast.LENGTH_SHORT).show();
                }else
                    {
                    userphone = clnumber.getText().toString().trim();
                    getCAlientDetails();
                }
            }
        });
        servicelits=new ArrayList<>();
     getServiceSpinnerData();
        spi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //  data_message.setText(getmsg(position));
                String s_price= (String) spi.getItemAtPosition(position);
                Log.i("assa","position========"+s_price);
                getPrice(s_price);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                getservice.setText("");
                getprice.setText("");


            }
        });

        bill=c.findViewById(R.id.bill);
        bill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent q=new Intent(getContext(),Showbill.class);
                startActivity(q);
            }
        });

        return c;}


    public void getServiceSpinnerData()
    {

        String SERVICE_URL="http://brilltechno.com//salon/android/selecsptservice.php";
        String c=SERVICE_URL+"?salon_id="+  sharedPreferences.getString("id", "");


        StringRequest stringRequest=new StringRequest(Request.Method.GET, c, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    Log.i("desfdgd", "msgspinnerdata============" + response);

                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject innerJSON = jsonArray.getJSONObject(i);
                        String service = innerJSON.getString("service");
                        servicelits.add(service);

                    }

                    spi.setAdapter(new ArrayAdapter<String>(getContext(),R.layout.support_simple_spinner_dropdown_item,servicelits));
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        requestQueue= Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);

    }
    private void getPrice(String s_price)
    {
        String pp=url+"?services="+s_price;

        StringRequest stringRequest=new StringRequest(Request.Method.GET, pp
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                String[] temp;
                temp=response.split("#");

                Log.i("hfhf","price response=========="+response);
                getservice.setText(temp[0]);
                getprice.setText(temp[1]);
                ser=getservice.getText().toString().trim();
                pr=getprice.getText().toString().trim();

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if(error != null){

                            Toast.makeText(getContext(), "", Toast.LENGTH_LONG).show();
                        }


                    }
                });

        requestQueue= Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);

    }






    public void getCAlientDetails()
    {
        String url1="http://brilltechno.com/salon/android/searchclientdata.php?phone="+userphone;

        Log.i("sdjkkjsvkj","getCAlientDetails======="+url1);
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.i("sdf","responce details======="+response);

                String temp[];

                temp=response.split(",");
                userid=temp[0];
                clname.setText(temp[1]);
                Birthday.setText(temp[2]);

                cn1=clname.getText().toString().trim();
                cb=Birthday.getText().toString().trim();


            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.i("","form error"+error.toString());

                        if(error != null){

                            Toast.makeText(getContext(), "Something went wrong.", Toast.LENGTH_LONG).show();
                        }

                    }
                });
        requestQueue= Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);

    }
    private void saveRequest() {
        n=clname.getText().toString().trim();
        p=Birthday.getText().toString().trim();
        nu=clnumber.getText().toString().trim();
        s=getservice.getText().toString().trim();
        tot=total.getText().toString().trim();
        //sex=spn.getSelectedItemPosition();
        final ProgressDialog mDialog = new ProgressDialog(getContext());
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setMessage("Loading...");
        mDialog.show();
       String URL_SAVE="http://brilltechno.com/salon/android/billuser.php";

        String url1 = URL_SAVE + "?name=" + n +"&phone="+nu+ "&date_of_birth=" + p+ "&services=" + s +"&price="+tot+"&salon_id="+  sharedPreferences.getString("id", "");
        ;

        Log.i("asdf", "url==========" + URL_SAVE);

        StringRequest request = new StringRequest(Request.Method.GET, url1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response)
                    {
                        mDialog.dismiss();
                        Log.i("asd", "api response=======" + response);
                        Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
                        clname.setText("");
                        clnumber.setText("");
                        Birthday.setText("");
                        getservice.setText("");
                        getprice.setText("");

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                         mDialog.dismiss();
                        Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_LONG).show();
                    }
                }) ;


        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(request);
    }
}
