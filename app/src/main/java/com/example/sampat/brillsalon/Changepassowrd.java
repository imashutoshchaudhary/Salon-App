package com.example.sampat.brillsalon;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class Changepassowrd extends AppCompatActivity {
    public static String urll="http://www.brilltechno.com/salon/android/forgot.php";
    TextView e;
    EditText chpass;
    Button save;
    String p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepassowrd);
        e=findViewById(R.id.e);
        chpass=findViewById(R.id.chpass);
        save=findViewById(R.id.save);
        e.setText(Forgot.em);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p=chpass.getText().toString().trim();
                if(p.isEmpty()){
                    Toast.makeText(Changepassowrd.this, "Enter password", Toast.LENGTH_SHORT).show();
                }
                else {
                    saveRequest();
                }

            }
        });
    }
    private void saveRequest() {
       p=chpass.getText().toString().trim();
        final ProgressDialog mDialog = new ProgressDialog(this);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setMessage("Loading...");
        mDialog.show();


        String url1 = urll + "?email=" + e +"&password="+p ;

        Log.i("asdf", "url==========" + urll);

        StringRequest request = new StringRequest(Request.Method.GET, url1,
                new Response.Listener<String>() {
                    @SuppressLint("WrongConstant")
                    @Override
                    public void onResponse(String response)
                    {
                        mDialog.dismiss();

                        Log.i("asdf", "api response=======" + response);
                        Toast.makeText(Changepassowrd.this, response, Toast.LENGTH_LONG).show();
                        Intent l=new Intent(Changepassowrd.this,login.class);
                        startActivity(l);
                       chpass.setText("");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //  mDialog.dismiss();
                        Toast.makeText(Changepassowrd.this, "Something went wrong", Toast.LENGTH_LONG).show();
                    }
                }) ;


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
        // NetworkCalls.getInstance().addToRequestQueue(request);


    }

}
