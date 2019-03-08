package com.example.sampat.brillsalon;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class Adaptertemplate extends RecyclerView.Adapter<Adaptertemplate.Re> {
    RequestQueue requestQueue;
    private ArrayList<Producttemplate> temp;
    Context context;
    public Adaptertemplate(Context context,ArrayList<Producttemplate> temp) {
        this.context=context;
        this.temp=temp;
    }

    @Override
    public Adaptertemplate.Re onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inf = LayoutInflater.from(parent.getContext());
        View view = inf.inflate(R.layout.listtemplate, parent, false);
        return new Adaptertemplate.Re(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Re holder, final int position) {
        final  Producttemplate product1 = temp.get(position);
        holder.sha.setText(product1.getTitle());
        holder.n.setText(product1.getMessage());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog de = new Dialog(context);
                de.setContentView(R.layout.delete);
                Button yes = de.findViewById(R.id.yes);
                ImageView img = de.findViewById(R.id.img);
                Button no = de.findViewById(R.id.no);
                img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        de.dismiss();
                    }
                });

                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        de.dismiss();
                    }
                });
                de.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                de.show();
                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        //  String delete_url = "www.brilltechno.com/salon/android/deleteclientdata.php";
                        Log.i("sdsdsf", "getid=======" + product1.getUid());

                        deleteClient(product1.getUid(), position);
                        de.dismiss();

                        // final GetDataAdapterClient getDataAdapter1 =  getDataAdapter.get(position);

                    }


                });
            }
        });


    }


    @Override
    public int getItemCount() {
        return temp.size();
    }

    public static class Re extends RecyclerView.ViewHolder {
        TextView sha, n,delete;

        public Re(View itemView) {
            super(itemView);
            sha = itemView.findViewById(R.id.sh);
            n = itemView.findViewById(R.id.n);
            delete=itemView.findViewById(R.id.delete);


        }
    }
    public void deleteClient(int uid, final int pos)
    {
        String url="http://brilltechno.com//salon/android/messagedelete.php?id="+uid+"";

        Log.i("jksfjsfjks","deleteClient=================="+url);
        StringRequest stringRequest=new StringRequest(Request.Method.GET,url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if(response.equals("data deleted"))
                        {
                            removeItem(pos);
                        }


                        Log.i("asdfdsfsdfsdafsadf","deleteuser=================="+response);



                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("wewdssf","data error"+error);
                    }
                });
        requestQueue= Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    private void removeItem(int p0) {
        temp.remove(p0);
        notifyItemRemoved(p0);
        notifyItemRangeChanged(p0, temp.size());


    }



}
