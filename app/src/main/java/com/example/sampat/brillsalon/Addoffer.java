package com.example.sampat.brillsalon;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the

 * create an instance of this fragment.
 */
public class Addoffer extends Fragment {
    EditText ofname,price,coupn;
    private static final String URL_SAVE = "http://www.brilltechno.com/salon/android/addoffer.php";
    Button addo;
    String on,p,cp;
    SharedPreferences sharedPreferences;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
       View z= inflater.inflate(R.layout.fragment_addoffer, container, false);
       addo=z.findViewById(R.id.addo);
       ofname=z.findViewById(R.id.ofname);
       price=z.findViewById(R.id.price);
       coupn=z.findViewById(R.id.coupn);
       addo.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               validation();   }
       });

        sharedPreferences =getContext().getSharedPreferences("prefUserDetail", MODE_PRIVATE);
        sharedPreferences.getString("id", "");


        return z;}
    private void saveRequest() {
        on = ofname.getText().toString().trim();
        p = price.getText().toString().trim();
        cp= coupn.getText().toString().trim();
        final ProgressDialog mDialog = new ProgressDialog(getContext());
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setMessage("Loading...");
        mDialog.show();

        String url1 = URL_SAVE + "?name=" + on+"&amount="+p+ "&coupon_code=" + cp+"&salon_id="+sharedPreferences.getString("id", "") ;


        Log.i("asdf", "url==========" + URL_SAVE);

        StringRequest request = new StringRequest(Request.Method.GET, url1,
                new Response.Listener<String>() {
                    @SuppressLint("WrongConstant")
                    @Override
                    public void onResponse(String response)
                    {
                        mDialog.dismiss();
                        Log.i("asdf", "api response=======" + response);
                        Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
                        ofname.setText("");
                        price.setText("");
                        coupn.setText("");

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
        // NetworkCalls.getInstance().addToRequestQueue(request);


    }
    public void validation(){
        Log.d(TAG, "register");

        if(!validate()){

            RegisterFailed();
            return;
        }

        addo.setEnabled(true);
        RegisterSuccess();
    }

    public void RegisterSuccess(){

        addo.setEnabled(true);
        saveRequest();

        // Toast.makeText(getContext(), "SignUP Successful", Toast.LENGTH_SHORT).show();

    }
    public void RegisterFailed(){

        Toast.makeText(getContext(), "Fill all entries", Toast.LENGTH_SHORT).show();
        addo.setEnabled(true);
    }

    public boolean validate()
    {
        boolean valid =true;

        String nam=ofname.getText()+"";
        String pass=price.getText()+"";
        String mobileno=coupn.getText()+"";



        if(nam.isEmpty()){

            ofname.setError("Enter Offer name");
            valid=false;
        }
        else {
            ofname.setError(null);
        }

        if(pass.isEmpty()){

            price.setError("Enter email");
            valid=false;
        }
        else {
            price.setError(null);
        }
        if(mobileno.isEmpty()){

            coupn.setError("Enter mobile no.");
            valid=false;
        }
        else {
            coupn.setError(null);
        }
        return valid;
        }
    }
