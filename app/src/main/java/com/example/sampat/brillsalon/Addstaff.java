package com.example.sampat.brillsalon;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.taishi.flipprogressdialog.FlipProgressDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the

 */
public class Addstaff extends Fragment {

    Spinner spn;
    EditText Birthday,addre,sname,semail,snumber,ssalary;
    Button add;
    private static final String URL_SAVE = "http://www.brilltechno.com/salon/android/staff.php";
    String sn,se,snu,ss,sa,sb;
    List<Integer> imagelist=new ArrayList<>();
    SharedPreferences sharedPreferences;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        View v= inflater.inflate(R.layout.fragment_addstaff, container, false);

        spn=v.findViewById(R.id.spn);
        Birthday=v.findViewById(R.id.Birthday);
        add=v.findViewById(R.id.add);
        addre=v.findViewById(R.id.addre);
        sname=v.findViewById(R.id.sname);
        semail=v.findViewById(R.id.semail);
        snumber=v.findViewById(R.id.snumber);
        imagelist.add(R.drawable.t1);
        imagelist.add(R.drawable.t2);
        imagelist.add(R.drawable.t3);
        imagelist.add(R.drawable.t5);
        sharedPreferences =getContext().getSharedPreferences("prefUserDetail", MODE_PRIVATE);
        sharedPreferences.getString("id", "");

        ssalary=v.findViewById(R.id.ssalary);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validation();
            }
        });
        String dd[]={"Gender","Male","Female"};

        ArrayAdapter<String> d=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item,dd);
        spn.setAdapter(d);
        final Calendar myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

            private void updateLabel() {
                String myFormat = "MM/dd/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                Birthday.setText(sdf.format(myCalendar.getTime()));
            }
        };

        Birthday.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        return v; }

    public void Progress(String na,String n){
        final FlipProgressDialog flip=new FlipProgressDialog();
        flip.setImageList(imagelist);
        flip.setOrientation(na);
        flip.setBackgroundColor((Color.TRANSPARENT));
        if (na.equals("rotationY")){
            flip.setBackgroundAlpha(0.2f);
        }
        else if(na.equals("rotationX")) {
            flip.setBackgroundAlpha(1.0f);
        }
        else
            flip.setBackgroundAlpha(0.3f);
        if(n.equals("fast")){
            flip.setDuration(300);
        }
        else if(n.equals("slow")){
            flip.setDuration(800);
        }
        else if(n.equals("dim"))
            flip.setDimAmount(0.8f);
        else if(n.equals("border")) {
            flip.setBorderColor(Color.parseColor("#FF0824C4"));
            flip.setBorderStroke(100);
        }
        else if (n.equals("alpha"))
            flip.setMinAlpha(1.0f);
        else if(n.equals("360"))
            flip.setEndAngle(360.0f);
        else if(n.equals("circle"))
            flip.setCornerRadius(200);
        flip.show(getActivity().getFragmentManager(),"");
        new android.os.Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        flip.dismiss();

                    }
                },2100);


    }
    private void saveRequest() {
        sn = sname.getText().toString().trim();
        se= semail.getText().toString().trim();
        snu = snumber.getText().toString().trim();
        sb=Birthday.getText().toString().trim();
        sa=addre.getText().toString().trim();
        ss=ssalary.getText().toString().trim();
        //sex=spn.getSelectedItemPosition();
      /*  final ProgressDialog mDialog = new ProgressDialog(getContext());
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setMessage("Loading...");
        mDialog.show();*/

        String url1 = URL_SAVE + "?name=" + sn +"&email="+se+ "&phone=" + sn + "&dob=" + sb+"&address="+sa+"&salary="+ss+"&salon_id="+ sharedPreferences.getString("id", "");
        ;

        Log.i("asdf", "url==========" + URL_SAVE);

        StringRequest request = new StringRequest(Request.Method.GET, url1,
                new Response.Listener<String>() {
                    @SuppressLint("WrongConstant")
                    @Override
                    public void onResponse(String response)
                    {
                     //   mDialog.dismiss();
                        Progress("rotationX","");
                        Log.i("asdf", "api response=======" + response);
                        Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
                        sname.setText("");
                        semail.setText("");
                        snumber.setText("");
                        addre.setText("");
                        ssalary.setText("");
                        Birthday.setText("");

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                       // mDialog.dismiss();
                        Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_LONG).show();
                    }
                }) ;


        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(request);
        // NetworkCalls.getInstance().addToRequestQueue(request);

    }
    public void validation(){
        Log.d(TAG, "register");

        if(!validate()){

            RegisterFailed();
            return;
        }

        add.setEnabled(true);
        RegisterSuccess();
    }




    public void RegisterSuccess(){

        add.setEnabled(true);
        saveRequest();

        // Toast.makeText(getContext(), "SignUP Successful", Toast.LENGTH_SHORT).show();

    }






    public void RegisterFailed(){

        Toast.makeText(getContext(), "Fill all entries", Toast.LENGTH_SHORT).show();
        add.setEnabled(true);
    }

    public boolean validate()
    {
        boolean valid =true;

        String n=sname.getText()+"";
        String e=semail.getText()+"";
        String m=snumber.getText()+"";
        String s=ssalary.getText()+"";
        String a=addre.getText()+"";
        String b=Birthday.getText()+"";



        if(n.isEmpty()){

            sname.setError("Enter name");
            valid=false;
        }
        else {
            sname.setError(null);
        }

        if(e.isEmpty()){

            semail.setError("Enter email");
            valid=false;
        }
        else {
            semail.setError(null);
        }
        if(m.isEmpty()){

            snumber.setError("Enter mobile no.");
            valid=false;
        }
        else {
            snumber.setError(null);
        }
        if(s.isEmpty()){

            ssalary.setError("Choose DOB");
            valid=false;
        }
        else {
            ssalary.setError(null);
        }
        if(a.isEmpty()){

            addre.setError("Choose DOB");
            valid=false;
        }
        else {
            addre.setError(null);
        }
        if(b.isEmpty()){

            Birthday.setError("Choose DOB");
            valid=false;
        }
        else {
            Birthday.setError(null);
        }





        return valid;






    }
}
