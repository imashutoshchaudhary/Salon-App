package com.example.sampat.brillsalon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Editprofile extends AppCompatActivity {
    EditText name,email,number,address;
    Button add;
    public static String n,e,nu,a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        number=findViewById(R.id.number);
        address=findViewById(R.id.address);
        add=findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                n=name.getText().toString().trim();
                e=email.getText().toString().trim();
                nu=number.getText().toString().trim();
                a=address.getText().toString();
                Intent k=new Intent(Editprofile.this,Profile.class);
                startActivity(k);
            }
        });
        }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Intent j=new Intent(Editprofile.this,Profile.class);
                startActivity(j);
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
