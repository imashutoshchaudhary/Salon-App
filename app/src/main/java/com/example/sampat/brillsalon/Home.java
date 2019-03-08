package com.example.sampat.brillsalon;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

public class Home extends AppCompatActivity  {
    FloatingActionButton fab_cs,fab_call,fab_email,fab_wa;
    Animation fab_open,fab_close,fab_clockwise,fab_anticlockwise;
    SharedPreferences preferences;
    Dialog dia;
    ImageView img;
    int doubleBackToExitPressed=1;
    CardView client,staff,service,stock,balance,reminder,template,sold,upload,addoffers,bill;
  Toolbar hometool;
    boolean isopen=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        fab_cs=findViewById(R.id.fab_cs);
        fab_call=findViewById(R.id.fab_call);
        fab_email=findViewById(R.id.fab_email);
        fab_wa=findViewById(R.id.fab_wa);
        client=findViewById(R.id.client);
        staff=findViewById(R.id.staff);
        service=findViewById(R.id.service);
        stock=findViewById(R.id.stock);
        upload=findViewById(R.id.upload);
        addoffers=findViewById(R.id.addoffers);
        reminder=findViewById(R.id.reminders);
        sold=findViewById(R.id.sold);
        balance=findViewById(R.id.balance);
        template=findViewById(R.id.template);
        hometool=findViewById(R.id.hometool);
        bill=findViewById(R.id.bill);
        fab_open= AnimationUtils.loadAnimation(this,R.anim.fab_open);
        fab_close= AnimationUtils.loadAnimation(this,R.anim.fab_close);
        fab_clockwise= AnimationUtils.loadAnimation(this,R.anim.rotate_clockwise);
        fab_anticlockwise= AnimationUtils.loadAnimation(this,R.anim.rotate_anticlockwise);
        fab_cs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isopen){

                    fab_email.startAnimation(fab_close);
                    fab_call.startAnimation(fab_close);
                    fab_wa.startAnimation(fab_close);
                    fab_cs.startAnimation(fab_anticlockwise);
                    fab_email.setClickable(false);
                    fab_call.setClickable(false);
                    fab_wa.setClickable(false);
                    isopen=false;

                }
                else {
                    fab_email.startAnimation(fab_open);
                    fab_call.startAnimation(fab_open);
                    fab_wa.startAnimation(fab_open);
                    fab_cs.startAnimation(fab_clockwise);
                    fab_email.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.whatsapp");
                            //startActivity(launchIntent);

                            String smsNumber = "917877229253"; //without '+'
                            try {
                                Intent sendIntent = new Intent("android.intent.action.MAIN");
                                sendIntent.setAction(Intent.ACTION_SEND);
                                sendIntent.setType("text/plain");
                                sendIntent.putExtra(Intent.EXTRA_TEXT, "Hello!!!!!");
                                sendIntent.putExtra("jid", smsNumber + "@s.whatsapp.net");
                                sendIntent.setPackage("com.whatsapp");
                                startActivity(sendIntent);
                            } catch(Exception e) {
                                Toast.makeText(Home.this, "Error\n" + e.toString(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                    fab_call.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // String url = "tel:9352611401";
                            //Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(url));
                            Intent i = new Intent(Intent.ACTION_SEND);
                            i.setType("message/rfc822");
                            i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"sampatdey43@gmail.com"});
                            i.putExtra(Intent.EXTRA_SUBJECT, "");
                            i.putExtra(Intent.EXTRA_TEXT   , "");
                            try {
                                startActivity(Intent.createChooser(i, "Send mail..."));
                            } catch (android.content.ActivityNotFoundException ex) {
                                Toast.makeText(Home.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    fab_wa.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            int permission_all=1;
                            String[] p={Manifest.permission.CALL_PHONE};
                            if(!permission(Home.this,p)){
                                ActivityCompat.requestPermissions(Home.this,p,permission_all);
                            }
                            String  posted_by = "7877229253";

                            String uri = "tel:" + posted_by.trim() ;
                            Intent intent = new Intent(Intent.ACTION_CALL);
                            intent.setData(Uri.parse(uri));
                            startActivity(intent);
                        }
                    });
                    isopen=true;
                }
            }
        });

       setSupportActionBar(hometool);
       getSupportActionBar().setTitle("Home");
        bill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ne=new Intent(Home.this,Bill.class);
                startActivity(ne);
            }
        });
    client.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent n=new Intent(Home.this,Client.class);
            startActivity(n);
        }
    });
    staff.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent n=new Intent(Home.this,Staff.class);
            startActivity(n);
        }
    });
    service.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent n=new Intent(Home.this,Service.class);
            startActivity(n);
        }
    });
    stock.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent n=new Intent(Home.this,Stock.class);
            startActivity(n);
        }
    });
    upload.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent n=new Intent(Home.this,Sharephoto.class);
            startActivity(n);
        }
    });
    reminder.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent n=new Intent(Home.this,Reminder.class);
            startActivity(n);
        }
    });
    addoffers.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent n=new Intent(Home.this,Offers.class);
            startActivity(n);
        }
    });
    balance.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent n=new Intent(Home.this,Balancesheet.class);
            startActivity(n);
        }
    });
    sold.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent n=new Intent(Home.this,Soldproduct.class);
            startActivity(n);
        }
    });
    template.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent n=new Intent(Home.this,Printbill.class);
            startActivity(n);
        }
    });
    checkConnection();
    }
    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }
    public void checkConnection(){
        if(isOnline()){
           }else{
            dia=new Dialog(Home.this);
            dia.setContentView(R.layout.no);
            dia.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dia.show();
            img=dia.findViewById(R.id.img);
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dia.dismiss();
                }
            });
            Toast.makeText(Home.this, "Check your INTERNET connection",Toast.LENGTH_LONG).show();
        }
          // Intent f=new Intent(Home.this,Nointernet.class);
        //startActivity(f);}
    }
    public static boolean permission(Context cont,String... per){
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M&&cont!=null&&per!=null){
            for (String perm:per){
                if (ActivityCompat.checkSelfPermission(cont,perm)!= PackageManager.PERMISSION_GRANTED){
                    return false;
                }
            }
        }
        return true;
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       switch ( item.getItemId()) {
           //noinspection SimplifiableIfStatement
           case R.id.logout:


               preferences = getSharedPreferences("prefUserDetail", MODE_PRIVATE);

               preferences.edit().clear().commit();

               Intent i = new Intent(Home.this, login.class);
               startActivity(i);

               return true;
           case R.id.profile:
               Intent j=new Intent(Home.this,Profile.class);
               startActivity(j);
               finish();

           case R.id.membership:
               Intent j1=new Intent(Home.this,Membership.class);
               startActivity(j1);
               finish();

           }


        return super.onOptionsItemSelected(item);
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
