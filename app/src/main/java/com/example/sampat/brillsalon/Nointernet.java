package com.example.sampat.brillsalon;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Nointernet extends AppCompatActivity {
    int doubleBackToExitPressed=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nointernet);
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
