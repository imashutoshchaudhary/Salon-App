package com.example.sampat.brillsalon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

public class Balancesheet extends AppCompatActivity {
    TextView daily,monthly,weekly;
    CardView income,expendature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balancesheet);
        daily=findViewById(R.id.daily);
        weekly=findViewById(R.id.weekly);
        income=findViewById(R.id.income);
        expendature=findViewById(R.id.expendature);
        income.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j=new Intent(Balancesheet.this,Income.class);
                startActivity(j);
            }
        });
        expendature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m=new Intent(Balancesheet.this,Expendature.class);
                startActivity(m);
            }
        });
        monthly=findViewById(R.id.monthly);
        daily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent d=new Intent(Balancesheet.this,Daily.class);
                startActivity(d);
            }
        });
        monthly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent n=new Intent(Balancesheet.this,Monthly.class);
                startActivity(n);
            }
        });
        weekly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a=new Intent(Balancesheet.this,Weekly.class);
                startActivity(a);
            }
        });
    }
}
