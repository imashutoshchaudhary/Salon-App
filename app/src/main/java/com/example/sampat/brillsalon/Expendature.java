package com.example.sampat.brillsalon;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Expendature extends AppCompatActivity {
    private static final String URL_SAVE = "http://www.brilltechno.com/salon/android/stock.php";
    EditText dis,qn,pz,Birthday;
    Button ad;
    public String d,q,p,b;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expendature);
        dis=findViewById(R.id.dis);
        qn=findViewById(R.id.qn);
        pz=findViewById(R.id.pz);
        ad=findViewById(R.id.ad);
        sharedPreferences =getSharedPreferences("prefUserDetail", MODE_PRIVATE);
        sharedPreferences.getString("id", "");

        Birthday=findViewById(R.id.Birthday);
        final Calendar myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

            private void updateLabel() {
                String myFormat = "MM/dd/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                Birthday.setText(sdf.format(myCalendar.getTime()));
            }
        };

        Birthday.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(Expendature.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }
    public void login() {
        Log.d("ta", "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        onLoginSuccess();
        ad.setEnabled(true);

    }



    public void onLoginSuccess() {
        saveRequest();
        ad.setEnabled(true);

    }

    public void onLoginFailed() {
        Toast.makeText(Expendature.this, "Fill all entries", Toast.LENGTH_LONG).show();

        ad.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String s=dis.getText()+"";
        String b=pz.getText()+"";
        String q=qn.getText()+"";

        if (s.isEmpty() ) {
            dis.setError("enter Discription");

            valid = false;
        } else {
            dis.setError(null);

        }

        if (b.isEmpty() ) {
            pz.setError("enter Prize");
            //Toast.makeText(getContext(),"Sub service2",Toast.LENGTH_LONG).show();
            valid = false;
        } else {
            pz.setError(null);


        }
        if (q.isEmpty() ) {
            //Toast.makeText(getContext(),"Sub service3",Toast.LENGTH_LONG).show();
            qn.setError("enter Quntity");
            valid = false;
        } else {
            qn.setError(null);


        }

        return valid;
    }

    private void saveRequest() {
        d=dis.getText().toString();
        q=qn.getText().toString().trim();
        p=pz.getText().toString().trim();
        b=Birthday.getText().toString().trim();

        //sex=spn.getSelectedItemPosition();
        final ProgressDialog mDialog = new ProgressDialog(this);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setMessage("Loading...");
        mDialog.show();

        String url1 = URL_SAVE + "?&productname="+d+ "&quantity=" + q + "&price=" +p+"&date="+b+"&salon_id="+sharedPreferences.getString("id", "");
        ;

        Log.i("asdf", "url==========" + URL_SAVE);

        StringRequest request = new StringRequest(Request.Method.GET, url1,
                new Response.Listener<String>() {
                    @SuppressLint("WrongConstant")
                    @Override
                    public void onResponse(String response)
                    {
                        mDialog.dismiss();
                        Log.i("asdf", "api response=======" + response);
                        Toast.makeText(Expendature.this, response, Toast.LENGTH_LONG).show();
                        dis.setText("");
                        qn.setText("");
                        pz.setText("");
                        Birthday.setText("");

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        mDialog.dismiss();
                        Toast.makeText(Expendature.this, "Something went wrong", Toast.LENGTH_LONG).show();
                    }
                }) ;


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
        // NetworkCalls.getInstance().addToRequestQueue(request);


    }

}
