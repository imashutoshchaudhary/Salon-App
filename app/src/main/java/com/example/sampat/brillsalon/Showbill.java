package com.example.sampat.brillsalon;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Showbill extends AppCompatActivity {
    TextView name,email,numb,total,print;
    RecyclerView recb;
    String n,em,b,nu,s,p,cls,clp;
    Uri URI;
    ImageView image;
    RecyclerView.Adapter adp;
    ArrayList<Productserviceprice> pro;
    static Activity act;

    Context cont;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showbill);
        name=findViewById(R.id.name);
        pro=new ArrayList<>();
        email=findViewById(R.id.email);
        print=findViewById(R.id.print);
        numb=findViewById(R.id.numb);
      //  ser=findViewById(R.id.ser);
        recb=findViewById(R.id.recb);
        image=findViewById(R.id.image);
      //  per=findViewById(R.id.per);
        total=findViewById(R.id.total);
     //   ser.setText(Existingclient.name);
       // per.setText(Existingclient.price);
        name.setText(Existingclient.n);
        email.setText(Existingclient.p);
        numb.setText(Existingclient.nu);
        View rootview=getWindow().getDecorView().findViewById(android.R.id.content);
        total.setText(Existingclient.tot);


     print.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             int permission_all=1;
             String[] p={Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE};
             if(!permission(Showbill.this,p)){
                 ActivityCompat.requestPermissions(Showbill.this,p,permission_all);
             }


             Bitmap bitmap = takeScreenshot();
             saveBitmap(bitmap);

         }
     });
        recb.setLayoutManager(new LinearLayoutManager(this));
        adp=new Adapterbill(cont,pro);
        recb.setAdapter(adp);
        cls=Existingclient.name;
        clp=Existingclient.price;
        pro.add(new Productserviceprice(cls,clp));
        adp.notifyDataSetChanged();

    }
    public void wht() {
        String smsNumber = numb.getText().toString().trim();
        try {
            Intent sendIntent = new Intent("android.intent.action.MAIN");
            String path=Environment.getExternalStorageDirectory().getAbsolutePath();
            String filen="/Notes+/MyNote.png";
            File file=new File(path,filen);
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.setType("image/*");
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Hello!!!!!");
           // sendIntent.putExtra(Intent.EXTRA_STREAM,Uri.fromFile(file));
            sendIntent.putExtra("jid", smsNumber + "@s.whatsapp.net");
            sendIntent.setPackage("com.whatsapp");
            startActivity(sendIntent);
        } catch (Exception e) {
            Toast.makeText(Showbill.this, "Error\n" + e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public Bitmap takeScreenshot()
    {
        View rootView = findViewById(android.R.id.content).getRootView();
        rootView.setDrawingCacheEnabled(true);
        return rootView.getDrawingCache();
    }

    public void saveBitmap(Bitmap bitmap)
    {
        File dir=new File(Environment.getExternalStorageDirectory()+"/Notes+");
        if ((!dir.exists()))
        {
            dir.mkdirs();
        }
        File imagePath = new File(dir + "/MyNote.png");
        try
        {
            FileOutputStream fos = new FileOutputStream(imagePath);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
            send();
           // startEmailActivity(Showbill.this,imagePath);
          //  canvasView.clearCanvas();

        } catch (FileNotFoundException e) {
            Log.e("GREC", e.getMessage(), e);
        } catch (IOException e) {
            Log.e("GREC", e.getMessage(), e);
        }
        //Viewing ads
      }

      public void send() {
        em=email.getText().toString().trim();
        String path=Environment.getExternalStorageDirectory().getAbsolutePath();
        String filen="/Notes+/MyNote.png";
        File file=new File(path,filen);
         Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT,"Your INVOICE");
        emailIntent.putExtra(Intent.EXTRA_TEXT,"BILL");
        emailIntent.putExtra(Intent.EXTRA_EMAIL  , new String[]{em});
        emailIntent.putExtra(Intent.EXTRA_STREAM,Uri.fromFile(file));
        emailIntent.setType("image/*");
        startActivity(Intent.createChooser(emailIntent,"sampatdey2011@gmail.com"));
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
}
