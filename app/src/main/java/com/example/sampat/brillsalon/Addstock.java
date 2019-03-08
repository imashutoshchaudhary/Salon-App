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
public class Addstock extends Fragment {
    private static final String URL_SAVE = "http://www.brilltechno.com/salon/android/stock.php";

   private int  RESULT_LOAD_IMG=1;
    EditText qn,br,st,pr;
    private static final String TAG = "Stock";
    Button addst;
    String b,p,q,pri;
    SharedPreferences sharedPreferences;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        // Inflate the layout for this fragment
        View sa= inflater.inflate(R.layout.fragment_addstock, container, false);
        qn=sa.findViewById(R.id.qn);
        br=sa.findViewById(R.id.pr);
        pr=sa.findViewById(R.id.po);
        st=sa.findViewById(R.id.br);
        addst=sa.findViewById(R.id.addst);
        addst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               login();
            }
        });

        sharedPreferences =getContext().getSharedPreferences("prefUserDetail", MODE_PRIVATE);
        sharedPreferences.getString("id", "");



        return sa;}

    private void saveRequest() {
        b= st.getText().toString().trim();
        p = br.getText().toString().trim();
        q= qn.getText().toString().trim();
        pri = pr.getText().toString().trim();

        //sex=spn.getSelectedItemPosition();
        final ProgressDialog mDialog = new ProgressDialog(getContext());
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setMessage("Loading...");
        mDialog.show();

        String url1 = URL_SAVE + "?brandname=" +b+"&productname="+p+ "&quantity=" + q + "&price=" +pri+"&salon_id="+ sharedPreferences.getString("id", "");
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
                        st.setText("");
                        br.setText("");
                        qn.setText("");
                        pr.setText("");

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



    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

onLoginSuccess();
        addst.setEnabled(true);

           }



    public void onLoginSuccess() {
        saveRequest();
        addst.setEnabled(true);

    }

    public void onLoginFailed() {
        Toast.makeText(getContext(), "Fill all entries", Toast.LENGTH_LONG).show();

       addst.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String s=st.getText()+"";
        String b=br.getText()+"";
        String q=qn.getText()+"";
        String p=pr.getText()+"";

        if (s.isEmpty() ) {
          st.setError("enter Stock name");

            valid = false;
        } else {
            st.setError(null);

        }

        if (b.isEmpty() ) {
           br.setError("enter Brand name");
            //Toast.makeText(getContext(),"Sub service2",Toast.LENGTH_LONG).show();
            valid = false;
        } else {
         br.setError(null);


        }
        if (q.isEmpty() ) {
            //Toast.makeText(getContext(),"Sub service3",Toast.LENGTH_LONG).show();
         qn.setError("enter Quntity");
            valid = false;
        } else {
          qn.setError(null);


        }
        if (p.isEmpty() ) {
            //Toast.makeText(getContext(),"Sub service4",Toast.LENGTH_LONG).show();
       pr.setError("enter Price");
            valid = false;
        } else {
           pr.setError(null);


        }

        return valid;
    }


}
