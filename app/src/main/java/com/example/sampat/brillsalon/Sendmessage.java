package com.example.sampat.brillsalon;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

public class Sendmessage extends AppCompatActivity {
    Toolbar t;
    public static final String content = "content";

    Spinner spinner_msg_title;
    ArrayList<String> msgtitle;
    TextView tv_number;
    String MSG_SERVER="http://www.brilltechno.com/salon/android/msgtitlespinner.php";
String title;
    RequestQueue requestQueue;

    public static final String JSON_ARRAY = "result";
    private JSONArray result;

    private ProgressDialog pd;

    TextView data_message;

    Button SendMsg;
    String ttl;
    SharedPreferences sharedPreferences;
    ArrayList<String> array;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendmessage);
        t=findViewById(R.id.t);
        spinner_msg_title=findViewById(R.id.temp);
        data_message=findViewById(R.id.msg);
        SendMsg=findViewById(R.id.send);
        setSupportActionBar(t);
        getSupportActionBar().setTitle("Send Message");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
       // adp=new Adaptertemp(cont,productList);
        //temp.setAdapter(adp);
        sharedPreferences =getSharedPreferences("prefUserDetail", MODE_PRIVATE);
        sharedPreferences.getString("id", "");

        Bundle bundle = getIntent().getExtras();
        array = (ArrayList<String>) bundle.getStringArrayList("mobile");

        Log.i("length","send arraylength=="+array);


        Log.i("length","send arraylength=="+array.size());
        int len=array.size();
        int i;

        SendMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String msg=data_message.getText().toString();
                //   CallMsgApi(msg,"");

                for(int i=0;i<array.size();i++)
                {
                    Log.i("gffgfg","mmmmmmmmmmmmmmmmmmmmmmmmm==="+array.get(i));

                    CallMsgApi(msg,array.get(i));

                }

            }
        });


        msgtitle=new ArrayList<>();


        getmsgspinnerdata();

        spinner_msg_title.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String service=spinner_msg_title.getItemAtPosition(spinner_msg_title.getSelectedItemPosition()).toString();
                Toast.makeText(getApplicationContext(),service,Toast.LENGTH_LONG).show();


                //  data_message.setText(getmsg(position));
                 title= (String) spinner_msg_title.getItemAtPosition(position);
                Log.i("assa","position========"+title);
                selectMessage(title);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
               data_message.setText("");

            }
        });
    }

    public void CallMsgApi(String msg, String s)
    {
        //  msg="Brill Techno Pvt Ltd. Greetings you ! Best Of Luck For Your Exam.";
        String url="http://brilltechno.com/salon/android/SendSmsApi.php?message="+msg+"&phone="+s+"&salon_id="+sharedPreferences.getString("id", "");
        ;

        Log.i("kjvkkj","printsssss======="+s);
        Log.i("asdf","xxxxxxxxxxxxxxxresponse===="+url);

        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.i("asdg","xxxxxxxxxxxxxxxresponse===="+response);
                Toast.makeText(getApplicationContext(),"Send Message Successfully",Toast.LENGTH_LONG).show();

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("asdf","yyyyyyyyyyyyyyy response===="+error);
                    }
                });

        requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void selectMessage(String title) {

        String url= "http://brilltechno.com/salon/android/selecttitle.php?title="+title+"&salon_id="+ sharedPreferences.getString("id", "");
        ;
        //pd.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET,url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.i("erw","message response=========="+response);
                        data_message.setText(response);


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if(error != null){

                            Toast.makeText(getApplicationContext(), "Something went wrong.", Toast.LENGTH_LONG).show();
                        }
                    }
                }

        );

        requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    public void getmsgspinnerdata()
    {
        Log.i("gfgdgdr","getmsgspinnerdata=================="+MSG_SERVER);

        StringRequest stringRequest=new StringRequest(Request.Method.GET, MSG_SERVER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    Log.i("desfdgd", "msgspinnerdata============" + response);

                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject innerJSON = jsonArray.getJSONObject(i);
                        String service = innerJSON.getString("et");
                        msgtitle.add(service);

                    }

                    spinner_msg_title.setAdapter(new ArrayAdapter<String>(Sendmessage.this,R.layout.support_simple_spinner_dropdown_item,msgtitle));
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

        requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Intent k=new Intent(Sendmessage.this,Reminder.class);
                startActivity(k);
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
