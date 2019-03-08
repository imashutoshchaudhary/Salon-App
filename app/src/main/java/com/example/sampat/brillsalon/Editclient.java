package com.example.sampat.brillsalon;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class Editclient extends AppCompatActivity {

    Toolbar toolbar;
    EditText cletname,cletemail,cletphone;
    Button update;
    String ClientNameHolder1,ClientEmailHolder1,ClientPhoneHolder1;
    String Idholder,ClientNameHolder,ClientEmailHolder,ClientPhoneHolder;
    RequestQueue requestQueue;
    SharedPreferences sharedPreferences;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editclient);
        toolbar=findViewById(R.id.toolbar);
        cletname=findViewById(R.id.etclname);
        cletemail=findViewById(R.id.etclemail);
        cletphone=findViewById(R.id.etclphone);
        update=findViewById(R.id.update);
        setSupportActionBar(toolbar);
        sharedPreferences =getSharedPreferences("prefUserDetail", MODE_PRIVATE);
        sharedPreferences.getString("id", "");

        getSupportActionBar().setTitle("Update Client");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Idholder =getIntent().getStringExtra("uids");

        Log.i("asdasd","user idddddddddddddd======"+getIntent().getStringExtra("uids"));


        ClientNameHolder=getIntent().getStringExtra("name");
        cletname.setText(ClientNameHolder);
        Log.i("asdasd","user name===="+ClientNameHolder);

        ClientEmailHolder=getIntent().getStringExtra("email");
        cletemail.setText(ClientEmailHolder);
        Log.i("asdasd","user email===="+ClientEmailHolder);

        ClientPhoneHolder=getIntent().getStringExtra("phone");
        cletphone.setText(ClientPhoneHolder);
        Log.i("asdasd","user phone===="+ClientPhoneHolder);



        update.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               ClientRecordUpdate(ClientNameHolder1,ClientEmailHolder1,ClientPhoneHolder1);
           }
       });


    }
    public void ClientRecordUpdate(String ClientNameHolder1, String ClientEmailHolder1, String ClientPhoneHolder1)
    {
        ClientNameHolder1=cletname.getText().toString();
        ClientEmailHolder1=cletemail.getText().toString();
        ClientPhoneHolder1=cletphone.getText().toString();

        Log.i("gygugh","id holder===="+Idholder);
        Log.i("cshchjcshcd","name holder===="+ClientNameHolder1);
        Log.i("qiuqdud","email holder==="+ClientEmailHolder1);
        Log.i("dwjkdjd","phone holder==="+ClientPhoneHolder1);

        String url="http://brilltechno.com//salon/android/updateclient.php?sid="+Idholder+"?username="+ClientNameHolder1+"&useremail="+ClientEmailHolder1+"&userphone="+ClientPhoneHolder1+"&salon_id="+sharedPreferences.getString("id", "");
        ;

        Log.i("asdf","urloooooooooooo"+url);


        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {

                Log.i("trgdfd","update responce====="+response);
                Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                        Log.i("gyghjggh","update error====="+error.toString());

                    }
                });

        requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
    public boolean onSupportNavigateUp()
    {
        finish();
        return true;
    }






    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Intent n=new Intent(Editclient.this,Client.class);
                startActivity(n);
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
