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

 *
 * create an instance of this fragment.
 */
public class AddclFragment extends Fragment {

    private static final String URL_SAVE = "http://www.brilltechno.com/salon/android/client.php";
    EditText name,phone,address;
    Spinner spn;
    SharedPreferences sharedPreferences;
    EditText date_of_birth;
    Button add;
  String name1, pass, dob,email;
    List<Integer> imagelist=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        setHasOptionsMenu(true);

        View view = inflater.inflate(R.layout.fragment_addcl, container, false);
        spn = view.findViewById(R.id.spn);
        name = view.findViewById(R.id.clname);
        phone = view.findViewById(R.id.clnum);
        address=view.findViewById(R.id.clemail);
        date_of_birth = view.findViewById(R.id.Birthday);
        add=view.findViewById(R.id.add);
        imagelist.add(R.drawable.t1);
        imagelist.add(R.drawable.t2);
        imagelist.add(R.drawable.t3);
        imagelist.add(R.drawable.t5);
        sharedPreferences =getContext().getSharedPreferences("prefUserDetail", MODE_PRIVATE);
        sharedPreferences.getString("id", "");

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  Intent c = new Intent(getContext(), Staff.class);
                startActivity(c);*/
              validation();
                /*GetData();

                InsertData(TempName, TempNum,TempDOB,TempEmail);*/

            }
        });
        String dd[] = {"Gender", "Male", "Female"};

        ArrayAdapter<String> d = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, dd);
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

                date_of_birth.setText(sdf.format(myCalendar.getTime()));
            }
        };

        date_of_birth.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        return view;
    }
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
        name1 = name.getText().toString().trim();
        pass = phone.getText().toString().trim();
        dob = date_of_birth.getText().toString().trim();
        email = address.getText().toString().trim();

        //sex=spn.getSelectedItemPosition();
       /* final ProgressDialog mDialog = new ProgressDialog(getContext());
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setMessage("Loading...");
        mDialog.show();*/

        String url1 = URL_SAVE + "?name=" + name1 +"&email="+email+ "&phone=" + pass + "&date_of_birth=" + dob +"&salon_id="+  sharedPreferences.getString("id", "");
        ;

        Log.i("asdf", "url==========" + URL_SAVE);

        StringRequest request = new StringRequest(Request.Method.GET, url1,
                new Response.Listener<String>() {
                    @SuppressLint("WrongConstant")
                    @Override
                    public void onResponse(String response)
                    {
                       Progress("rotationY","");

                        Log.i("asdf", "api response=======" + response);
                        Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
                        name.setText("");
                        phone.setText("");
                        date_of_birth.setText("");
                        address.setText("");

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                      //  mDialog.dismiss();
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

        String nam=name.getText()+"";
        String pass=phone.getText()+"";
        String mobileno=address.getText()+"";
        String b=date_of_birth.getText()+"";



        if(nam.isEmpty()){

            name.setError("Enter name");
            valid=false;
        }
        else {
            name.setError(null);
        }

        if(pass.isEmpty()){

            phone.setError("Enter email");
            valid=false;
        }
        else {
            phone.setError(null);
        }
        if(mobileno.isEmpty()){

            address.setError("Enter mobile no.");
            valid=false;
        }
        else {
            address.setError(null);
        }
        if(b.isEmpty()){

            date_of_birth.setError("Choose DOB");
            valid=false;
        }
        else {
            date_of_birth.setError(null);
        }



        return valid;






    }}