package com.example.sampat.brillsalon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Forgot extends AppCompatActivity {
    EditText email;
    Button change;
   public static String em;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        email=findViewById(R.id.email);
        change=findViewById(R.id.change);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                em=email.getText().toString().trim();
                if(em.isEmpty()){
                    email.setError("Enter Correct usernmae or email");
                }
                else {
                    Intent k=new Intent(Forgot.this,Changepassowrd.class);
                    startActivity(k);
                }
            }
        });

       }
}
