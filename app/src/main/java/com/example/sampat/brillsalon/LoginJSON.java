package com.example.sampat.brillsalon;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class LoginJSON extends AsyncTask<String , Void , String> {

    Context cont;
    LoginJSON(Context ct){
        cont=ct;

    }
AlertDialog alert;

    @Override
    protected String doInBackground(String... parents) {
        String type=parents[0];
        String registeredURL="http://www.brilltechno.com/salon/android/login.php";
        if (type.equals("login"))
        {
            try {
                String email=parents[1];
                String password=parents[2];
                URL url=new URL(registeredURL);
                HttpURLConnection http=(HttpURLConnection)url.openConnection();
                http.setRequestMethod("POST");
                http.setDoOutput(true);
                http.setDoInput(true);
                OutputStream out=http.getOutputStream();
                BufferedWriter buffer=new BufferedWriter(new OutputStreamWriter(out,"UTF-8"));
                String post= URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"
                        + URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                buffer.write(post);
                buffer.flush();
                buffer.close();
                out.close();
                InputStream in=http.getInputStream();
                BufferedReader bufferreader=new BufferedReader(new InputStreamReader(in,"iso-8859-1"));
                String result="";
                String line="";
                while ((line=bufferreader.readLine())!=null){
                    result+=line;
                }
                bufferreader.close();
                in.close();
                http.disconnect();
                return result;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPreExecute(){


        alert=new AlertDialog.Builder(cont).create();
        alert.setTitle("Loginnnn");
       //Toast.makeText(this, "done", Toast.LENGTH_SHORT).show();
      /*  final ProgressDialog progressDialog = new ProgressDialog(this,
                R.style.Theme_AppCompat_Light_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        progressDialog.show();
        new android.os.Handler().postDelayed(
                new Runnable() {

                    public void run() {
                        Intent n = new Intent(,Client.class);
                        startActivity(n);
                        // On complete call either onLoginSuccess or onLoginFailed

                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 3400);*/
    }

    @Override
    protected void onPostExecute(String result) {
        alert.setMessage(result);
        alert.show();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
