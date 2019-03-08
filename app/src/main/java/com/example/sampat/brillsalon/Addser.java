package com.example.sampat.brillsalon;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.

 * create an instance of this fragment.
 */
public class Addser extends Fragment {
    private static final String URL_SAVE = "http://www.brilltechno.com/salon/android/subservice.php";
    String s,p;
    EditText serv,pz;
    Button ad;
    SharedPreferences sharedPreferences;
    private static final String TAG = "Service";

    // TODO: Rename and change types of parameters
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        // Inflate the layout for this fragment
        View d= inflater.inflate(R.layout.fragment_addser, container, false);
        serv=d.findViewById(R.id.serv);
        pz=d.findViewById(R.id.pz);
        ad=d.findViewById(R.id.ad);
        sharedPreferences =getContext().getSharedPreferences("prefUserDetail", MODE_PRIVATE);
        sharedPreferences.getString("id", "");

        ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

    return d;}

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        ad.setEnabled(true);
onLoginSuccess();
    }



    public void onLoginSuccess() {
        ad.setEnabled(true);
        saveRequest();

    }

    public void onLoginFailed() {
        Toast.makeText(getContext(), "Fill all entries", Toast.LENGTH_LONG).show();

        ad.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String su=serv.getText()+"";
        String p=pz.getText()+"";

        if (su.isEmpty() ) {
            serv.setError("Enter Sub Service");
            valid = false;
        } else {
            serv.setError(null);

        }

        if (p.isEmpty() ) {

            pz.setError("enter Price");
            valid = false;
        } else {
            pz.setError(null);


        }
        return valid;
    }
    private void saveRequest() {
        s = serv.getText().toString().trim();
        p = pz.getText().toString().trim();

        //sex=spn.getSelectedItemPosition();
        final ProgressDialog mDialog = new ProgressDialog(getContext());
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setMessage("Loading...");
        mDialog.show();

        String url1 = URL_SAVE + "?sub_services=" + s +"&price="+p+"&salon_id="+ sharedPreferences.getString("id", "");
        ;

        Log.i("asdf", "url==========" + URL_SAVE);

        StringRequest request = new StringRequest(Request.Method.GET, url1,
                new Response.Listener<String>() {
                    @SuppressLint("WrongConstant")
                    @Override
                    public void onResponse(String response)
                    {
                        mDialog.dismiss();
                        Log.i("asdf", "api response=======" + response);
                        Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
                        serv.setText("");
                        pz.setText("");
                            }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        mDialog.dismiss();
                        Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_LONG).show();
                    }
                }) ;


        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(request);
        // NetworkCalls.getInstance().addToRequestQueue(request);


    }

}
