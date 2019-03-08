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
 * Activities that contain this fragment must implement the
 * create an instance of this fragment.
 */
public class Addtemplate extends Fragment {

    private static final String URL_SAVE = "http://www.brilltechno.com/salon/android/sms_template.php";
    EditText title,message;
    Button add;
    String t,m;
    SharedPreferences sharedPreferences;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        View b= inflater.inflate(R.layout.fragment_addtemplate, container, false);
        title=b.findViewById(R.id.title);
        message=b.findViewById(R.id.message);
        add=b.findViewById(R.id.add);
        sharedPreferences =getContext().getSharedPreferences("prefUserDetail", MODE_PRIVATE);
        sharedPreferences.getString("id", "");
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveRequest();
            }
        });

    return b;}
    private void saveRequest() {
        t = title.getText().toString().trim();
        m = message.getText().toString().trim();
        //sex=spn.getSelectedItemPosition();
        final ProgressDialog mDialog = new ProgressDialog(getContext());
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setMessage("Loading...");
        mDialog.show();

        String url1 = URL_SAVE + "?title=" + t +"&message"+m+"&salon_id="+ sharedPreferences.getString("id", "");
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
                        title.setText("");
                        message.setText("");

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
