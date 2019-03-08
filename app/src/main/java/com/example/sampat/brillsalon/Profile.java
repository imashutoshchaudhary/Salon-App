package com.example.sampat.brillsalon;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.FileNotFoundException;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class Profile extends AppCompatActivity {
    private static String url="http://www.brilltechno.com/salon/android/upload_image.php";
    CircleImageView profile,proo;
    ImageView cam;
    ImageView edit;
    TextView n,e,nu,a;
    Toolbar protool;
    Button logout;
Dialog pro;
  Bitmap selectedImage;
    private int RESULT_LOAD_IMG=1;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        profile=findViewById(R.id.profile);
        cam=findViewById(R.id.cam);
        n=findViewById(R.id.n);
        e=findViewById(R.id.e);
        nu=findViewById(R.id.nu);
        a=findViewById(R.id.a);
       /* n.setText(Editprofile.n);
        e.setText(Editprofile.e);
        nu.setText(Editprofile.nu);
        a.setText(Editprofile.a);*/
        logout=findViewById(R.id.logout);
        protool=findViewById(R.id.protool);
        setSupportActionBar(protool);
        getSupportActionBar().setTitle("Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        edit=findViewById(R.id.edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Profile.this,Editprofile.class);
                startActivity(i);
            }
        });
        cam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);

            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pro=new Dialog(Profile.this);
                pro.setContentView(R.layout.profile);
                proo=pro.findViewById(R.id.pro);
                proo.setImageBitmap(selectedImage);
                pro.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                pro.show();

                }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferences = getSharedPreferences("prefUserDetail", MODE_PRIVATE);

                preferences.edit().clear().commit();

                Intent i = new Intent(Profile.this, login.class);
                startActivity(i);


            }
        });
    }
    public void uploadimage(){

        String url1 = url + "?image=" + profile;

        StringRequest stringRequest=new StringRequest(Request.Method.GET, url1,
                new Response.Listener<String>() {
                    @SuppressLint("WrongConstant")
                    @Override
                    public void onResponse(String response) {
                        Log.i("asdf", "api response=======" + response);
                        Toast.makeText(Profile.this, response, Toast.LENGTH_LONG).show();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Profile.this, "Something went wrong", Toast.LENGTH_LONG).show();

            }
        });
           /* @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("image",image();
                return params;
            }*/
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);



    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = this.getContentResolver().openInputStream(imageUri);
                selectedImage = BitmapFactory.decodeStream(imageStream);
                profile.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

        }else {
            Toast.makeText(this, "You haven't picked Image",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Intent j=new Intent(Profile.this,Home.class);
                startActivity(j);
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
