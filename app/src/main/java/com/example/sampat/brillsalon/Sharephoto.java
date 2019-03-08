package com.example.sampat.brillsalon;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class Sharephoto extends AppCompatActivity {
    Toolbar to;
    ImageView plus,plusa;
    RecyclerView recy;
    private int RESULT_LOAD_IMG=1;
ImageView imag;

    Intent data;
    private static String url="http://www.brilltechno.com/salon/android/upload_image.php";



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = this.getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                imag.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

        }else {
            Toast.makeText(this, "You haven't picked Image",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharephoto);
        plus = findViewById(R.id.plus);
        recy = findViewById(R.id.recy);
      imag=findViewById(R.id.imag);
      plusa=findViewById(R.id.plusa);
        to=findViewById(R.id.to);
        setSupportActionBar(to);
        getSupportActionBar().setTitle("Upload Photo");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        plusa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadimage();

            }
        });
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);
            }
        });
        recy.setLayoutManager(new LinearLayoutManager(this));
        int n[]={R.drawable.sm,R.drawable.sm,R.drawable.sm};
        int m[]={R.drawable.sm,R.drawable.sm,R.drawable.sm};
        recy.setAdapter(new Adapterupload(n,m));
        recy.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recy.setAdapter(new Adapterupload(n,m));

    }
    public void uploadimage(){
        final ProgressDialog mDialog = new ProgressDialog(this);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setMessage("Loading...");
        mDialog.show();

        String url1 = url + "?image=" + imag;

        StringRequest stringRequest=new StringRequest(Request.Method.GET, url1,
                new Response.Listener<String>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onResponse(String response) {
                mDialog.dismiss();
                Log.i("asdf", "api response=======" + response);
                Toast.makeText(Sharephoto.this, response, Toast.LENGTH_LONG).show();
                imag.setImageResource(0);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mDialog.dismiss();
                Toast.makeText(Sharephoto.this, "Something went wrong", Toast.LENGTH_LONG).show();

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
   /* private  String image(Bitmap bitmap){

        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte [] bytes=byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(bytes,Base64.DEFAULT);
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Intent m=new Intent(Sharephoto.this,Home.class);
                startActivity(m);
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
