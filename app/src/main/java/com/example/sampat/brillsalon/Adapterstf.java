package com.example.sampat.brillsalon;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
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

public class Adapterstf extends RecyclerView.Adapter<Adapterstf.Re> {

    RequestQueue requestQueue;
    private ArrayList<Productstaff> productLis;
    private Context context;


    public Adapterstf(Context context,ArrayList<Productstaff> productList) {
        this.context=context;
        this.productLis=productList;
    }

    @Override
    public Adapterstf.Re onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inf = LayoutInflater.from(parent.getContext());
        View view = inf.inflate(R.layout.listst, parent, false);
        return new Adapterstf.Re(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Re holder, final int position) {
        final  Productstaff product = productLis.get(position);
        holder.sh.setText(product.getCnames());
        holder.nu.setText(product.getCphone());
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent=new Intent(context,Editstaff.class);

                Log.i("fggfdfg","dfasdfs=============="+product.getUid());

                String uid= String.valueOf(product.getUid());

                // intent.putExtra("uids",getDataAdapter1.getUid());

                intent.putExtra("name",product.getCnames());
                intent.putExtra("email",product.getCemail());
                intent.putExtra("phone",product.getCphone());
                intent.putExtra("salary",product.getCsalary());
                intent.putExtra("address",product.getCaddress());
                intent.putExtra("uids",uid);
                context.startActivity(intent);


            }
        });
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
                        Log.i("sdsdsf", "getid=======" + product.getUid());

                        deleteClient(product.getUid(), position);
                        de.dismiss();

                        // final GetDataAdapterClient getDataAdapter1 =  getDataAdapter.get(position);

                    }


                });
            }
        });


    }
    public void Filter(ArrayList<Productstaff> newList)
    {

        productLis=new ArrayList<>();
        productLis.addAll(newList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return productLis.size();
    }

    public static class Re extends RecyclerView.ViewHolder {
        ImageView img;
        TextView sh, nu,edit,delete;

        public Re(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            sh = itemView.findViewById(R.id.sh);
            nu = itemView.findViewById(R.id.nu);
            edit=itemView.findViewById(R.id.edit);
            delete=itemView.findViewById(R.id.delete);


        }


    }
    public void deleteClient(int uid, final int pos)
    {
        String url="http://brilltechno.com//salon/android/deletestaffdata.php?id="+uid+"";

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
        productLis.remove(p0);
        notifyItemRemoved(p0);
        notifyItemRangeChanged(p0, productLis.size());


    }

}
