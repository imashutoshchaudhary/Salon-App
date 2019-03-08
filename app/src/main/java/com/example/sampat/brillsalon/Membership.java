package com.example.sampat.brillsalon;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Membership extends AppCompatActivity {

    Button btnHistory,btnStandard,btnPletinum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_membership);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnHistory=(Button)findViewById(R.id.btnHistory);
        btnStandard=(Button)findViewById(R.id.btnStandard);
        btnPletinum=(Button)findViewById(R.id.btnPletinum);

        beforeMethod();

        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnHistory.setBackground(getResources().getDrawable(R.drawable.mamberbasic_bbtn_actn2));
                btnStandard.setBackground(getResources().getDrawable(R.drawable.mamberstandard_bbtn_actn));
                btnPletinum.setBackground(getResources().getDrawable(R.drawable.mamberpletinum_bbtn_actn));

                BasicPlansFragment b=new BasicPlansFragment();
                FragmentManager fm=getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.fragmentPlans,b).commit();

            }
        });


        btnStandard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnHistory.setBackground(getResources().getDrawable(R.drawable.mamberbasic_bbtn_actn));
                btnStandard.setBackground(getResources().getDrawable(R.drawable.mamberstandard_bbtn_actn2));
                btnPletinum.setBackground(getResources().getDrawable(R.drawable.mamberpletinum_bbtn_actn));

                StandardPlansFragment b=new StandardPlansFragment();
                FragmentManager fm=getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.fragmentPlans,b).commit();
            }
        });



        btnPletinum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnHistory.setBackground(getResources().getDrawable(R.drawable.mamberbasic_bbtn_actn));
                btnStandard.setBackground(getResources().getDrawable(R.drawable.mamberstandard_bbtn_actn));
                btnPletinum.setBackground(getResources().getDrawable(R.drawable.mamberpletinum_bbtn_actn2));

                PlatinumPlansFragment b=new PlatinumPlansFragment();
                FragmentManager fm=getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.fragmentPlans,b).commit();
            }
        });

    }

    private void beforeMethod() {
        setTitle("Plan & Pricing");
        btnHistory.setBackground(getResources().getDrawable(R.drawable.mamberbasic_bbtn_actn2));
        btnStandard.setBackground(getResources().getDrawable(R.drawable.mamberstandard_bbtn_actn));
        btnPletinum.setBackground(getResources().getDrawable(R.drawable.mamberpletinum_bbtn_actn));

        BasicPlansFragment b=new BasicPlansFragment();
        FragmentManager fm=getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.fragmentPlans,b).commit();
    }
    public boolean onSupportNavigateUp()
    {
        finish();
        return true;
    }

}
