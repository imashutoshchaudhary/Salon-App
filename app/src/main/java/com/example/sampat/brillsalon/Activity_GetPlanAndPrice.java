package com.example.sampat.brillsalon;

import android.app.Activity;
import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import instamojo.library.InstamojoPay;
import instamojo.library.InstapayListener;

public class Activity_GetPlanAndPrice extends AppCompatActivity {

    SharedPreferences prefuserdtl;

    SharedPreferences prefPlanAndPrice;

    TextView txtName,txtdob,txtemail,txtphone,txtCountry,txtState,txtCity;
    Button btnPaymentGatway;

    TextView txtPlanName,txtDuration,txtprice,txtSubtotal,txtsubprice,txtDiscountprice,txttotalprice;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_plans);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSharedPrefDataPlanMethod();




        btnPaymentGatway.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              /*  txtName.setText(prefuserdtl.getString("name",""));
                txtdob.setText(prefuserdtl.getString("birthday",""));
                txtemail.setText(prefuserdtl.getString("email",""));
                txtphone.setText(prefuserdtl.getString("phone",""));
                txtCountry.setText(prefuserdtl.getString("country",""));
                txtState.setText(prefuserdtl.getString("state",""));
                txtCity.setText(prefuserdtl.getString("city",""));

*/
                prefPlanAndPrice=getSharedPreferences(Configs.planprice, Context.MODE_PRIVATE);
                txtPlanName.setText(prefPlanAndPrice.getString("planname",""));
                txtDuration.setText(prefPlanAndPrice.getString("planduration",""));
                txtprice.setText("\u20B9"+" "+prefPlanAndPrice.getString("planprice",""));
                txtsubprice.setText("\u20B9"+" "+prefPlanAndPrice.getString("planprice",""));

                txtDiscountprice.setText("-"+" "+"\u20B9"+"0");
                txttotalprice.setText(prefPlanAndPrice.getString("planprice",""));



                String product_name =prefPlanAndPrice.getString("planname","");
                String price =prefPlanAndPrice.getString("planprice","");
                String name =prefuserdtl.getString("name","");
                String phone =prefuserdtl.getString("phone","");
                String email =prefuserdtl.getString("email","");
                String days =prefPlanAndPrice.getString("planduration","");

                callInstamojoPay(email,phone,price,"Purchase plans",name,product_name,days);


            }
        });


    }


    private void getSharedPrefDataPlanMethod() {

        setTitle("Billing Details");

        txtName=(TextView)findViewById(R.id.txtName);
        txtdob=(TextView)findViewById(R.id.txtdob);
        txtemail=(TextView)findViewById(R.id.txtemail);
        txtphone=(TextView)findViewById(R.id.txtphone);
        txtCountry=(TextView)findViewById(R.id.txtCountry);
        txtState=(TextView)findViewById(R.id.txtState);
        txtCity=(TextView)findViewById(R.id.txtCity);

        txtPlanName=(TextView)findViewById(R.id.txtPlanName);
        txtDuration=(TextView)findViewById(R.id.txtDuration);
        txtprice=(TextView)findViewById(R.id.txtprice);

        txtsubprice=(TextView)findViewById(R.id.txtsubprice);
        txtDiscountprice=(TextView)findViewById(R.id.txtDiscountprice);
        txttotalprice=(TextView)findViewById(R.id.txttotalprice);
        btnPaymentGatway=(Button) findViewById(R.id.btnPaymentGatway);

        txtCity=(TextView)findViewById(R.id.txtCity);



        prefuserdtl=getSharedPreferences(Configs.UserPrefrance, Context.MODE_PRIVATE);

        txtName.setText(prefuserdtl.getString("name",""));
        txtdob.setText(prefuserdtl.getString("birthday",""));
        txtemail.setText(prefuserdtl.getString("email",""));
        txtphone.setText(prefuserdtl.getString("phone",""));
        txtCountry.setText(prefuserdtl.getString("country",""));
        txtState.setText(prefuserdtl.getString("state",""));
        txtCity.setText(prefuserdtl.getString("city",""));


        prefPlanAndPrice=getSharedPreferences(Configs.planprice, Context.MODE_PRIVATE);
        txtPlanName.setText(prefPlanAndPrice.getString("planname",""));
        txtDuration.setText(prefPlanAndPrice.getString("planduration",""));
        txtprice.setText("\u20B9"+" "+prefPlanAndPrice.getString("planprice",""));
        txtsubprice.setText("\u20B9"+" "+prefPlanAndPrice.getString("planprice",""));

        txtDiscountprice.setText("-"+" "+"\u20B9"+"0");
        txttotalprice.setText(prefPlanAndPrice.getString("planprice",""));




    }

    public boolean onSupportNavigateUp()
    {
        finish();
        return true;
    }


    private void callInstamojoPay(String email, String phone, String amount, String purpose, String buyername,String product_name,String days) {
        final Activity activity = this;
        InstamojoPay instamojoPay = new InstamojoPay();
        IntentFilter filter = new IntentFilter("ai.devsupport.instamojo");
        registerReceiver(instamojoPay, filter);
        JSONObject pay = new JSONObject();
        try {
            pay.put("email", email);
            pay.put("phone", phone);
            pay.put("purpose", purpose);
            pay.put("amount", amount);
            pay.put("name", buyername);
            pay.put("send_sms", true);
            pay.put("send_email", true);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        catch (Exception e) {

        }
        initListener();
        instamojoPay.start(activity, pay, listener);
    }

    InstapayListener listener;


    private void initListener() {
        listener = new InstapayListener() {
            @Override
            public void onSuccess(String response) {
                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG)
                        .show();
            }

            @Override
            public void onFailure(int code, String reason) {
                Toast.makeText(getApplicationContext(), "Failed: " + reason, Toast.LENGTH_LONG)
                        .show();
            }
        };
    }



}
