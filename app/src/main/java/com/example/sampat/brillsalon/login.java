package com.example.sampat.brillsalon;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.taishi.flipprogressdialog.FlipProgressDialog;

import java.util.ArrayList;
import java.util.List;

import static com.example.sampat.brillsalon.Showbill.permission;

public class login extends AppCompatActivity implements View.OnClickListener  {
    public static final String LOGIN_URL="http://www.brilltechno.com/salon/android/login.php";
    int doubleBackToExitPressed=1;
     EditText email1;
     EditText password1;
     Button log;
    Dialog epic;
    TextView forgot;
    ImageView img;
    Button go;
   public SharedPreferences preferences;
  String count1;
   int a;
    String username;
  String  password12;

List<Integer> imagelist=new ArrayList<>();


    RelativeLayout rellay1, rellay2;


    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            rellay1.setVisibility(View.VISIBLE);
            rellay2.setVisibility(View.VISIBLE);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email1= findViewById(R.id.editemail);
        forgot=findViewById(R.id.forgot);
        password1= findViewById(R.id.editpassword);
        log = (Button) findViewById(R.id.log);
       rellay1 = (RelativeLayout) findViewById(R.id.rellay1);
       rellay2 = (RelativeLayout) findViewById(R.id.rellay2);
        handler.postDelayed(runnable, 2000);

        int permission_all=1;
        String[] p={Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CALL_PHONE,Manifest.permission.READ_CALENDAR,Manifest.permission.WRITE_CALENDAR};
        if(!permission(login.this,p)){
            ActivityCompat.requestPermissions(login.this,p,permission_all);
        }

        log.setOnClickListener(this);
       imagelist.add(R.drawable.t44);
       imagelist.add(R.drawable.t22);
        imagelist.add(R.drawable.t33);
        imagelist.add(R.drawable.t11);
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent o=new Intent(login.this,Forgot.class);
                startActivity(o);
            }
        });

       }
   private void loginUser() {
       username =email1.getText().toString().trim();
       password12 = password1.getText().toString().trim();


       String url=LOGIN_URL+"?email="+username+"&password="+password12;
               Log.i("asdf","url=========="+url);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String count) {

                Log.i("asdf","api response======="+count);
                if (!count.equals(0)||!username.isEmpty()||!password12.isEmpty()){
                   openProfile();
                   count1=count;

                } else {

                    Toast.makeText(login.this, "failed", Toast.LENGTH_LONG).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(login.this, "Please check your INTERNET", Toast.LENGTH_LONG).
                    /* NotificationManager m1=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        Notification x=new Notification();
                        x.vibrate=new long[]{100,250,100,1000};
                        m1.notify(1,x);*/
                        epic=new Dialog(login.this);
                        epic.setContentView(R.layout.pop);
                        img=epic.findViewById(R.id.img);
                        go=epic.findViewById(R.id.go);

                        img.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                epic.dismiss();
                            }
                        });
                        epic.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        epic.show();
                        go.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                epic.dismiss();
                            }
                        });
                    }
                }) {
           /* @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put(KEY_USERNAME, username);
                params.put(KEY_PASSWORD, password12);
                return params;
            }*/
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    public void Progress(String na,String n){
        FlipProgressDialog flip=new FlipProgressDialog();
        flip.setImageList(imagelist);
        flip.setOrientation(na);
        flip.setBackgroundColor((Color.TRANSPARENT));
        if (na.equals("rotationY")){
            flip.setBackgroundAlpha(0.2f);
           }
           else if(na.equals("rotationX")) {
            flip.setBackgroundAlpha(9.0f);
        }
        else
            flip.setBackgroundAlpha(0.3f);
        if(n.equals("fast")){
            flip.setDuration(300);
        }
        else if(n.equals("slow")){
            flip.setDuration(800);
        }
        else if(n.equals("dim"))
            flip.setDimAmount(0.8f);
        else if(n.equals("border")) {
          flip.setBorderColor(Color.parseColor("#FF0824C4"));
          flip.setBorderStroke(100);
        }
        else if (n.equals("alpha"))
            flip.setMinAlpha(1.0f);
        else if(n.equals("360"))
            flip.setEndAngle(360.0f);
        else if(n.equals("circle"))
            flip.setCornerRadius(200);
        flip.show(getFragmentManager(),"");


    }
    private void openProfile(){
              /* final ProgressDialog progressDialog = new ProgressDialog(login.this,
                R.style.Theme_AppCompat_Light_Dialog);
      //  progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();*/
              Progress("rotationX","");


        // TODO: Implement your own authentication logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {

                    public void run() {

                      preferences=getSharedPreferences("prefUserDetail",MODE_PRIVATE);

                        SharedPreferences.Editor editor=preferences.edit();
                        editor.putString("email",username);
                        editor.putString("password",password12);
                        editor.putString("id",count1);
                        editor.commit();

                        Intent n=new Intent(
                                login.this,Home.class);
                        startActivity(n);
                        // On complete call either onLoginSuccess or onLoginFailed
                        // onLoginFailed();
                      //  progressDialog.dismiss();
                    }
                }, 2400);
    }

    @Override
    public void onClick(View view) {

        if (view == log){
            loginUser();
        }

    }
    public String getUemail() {
        SharedPreferences preferences;
        preferences=getSharedPreferences("prefUserDetail",MODE_PRIVATE);

        return preferences.getString(username,null);
    }



    @Override
    public void onBackPressed() {
        if(doubleBackToExitPressed==2){
            finishAffinity();
            System.exit(0);
        }
        else{
            doubleBackToExitPressed++;
            Toast.makeText(this, "Please press back again to EXIT ", Toast.LENGTH_SHORT).show();
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressed=1;
            }
        },2000);
    }
}

