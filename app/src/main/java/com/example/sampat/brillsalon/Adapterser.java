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

public class Adapterser extends RecyclerView.Adapter<Adapterser.Re> {
    RequestQueue requestQueue;
    private ArrayList<Productservice> productList;
    private Context context;
    public Adapterser(Context context,ArrayList<Productservice> productlist ) {
        this.context=context;
        this.productList = productlist;
    }
    @Override
    public Adapterser.Re onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inf = LayoutInflater.from(parent.getContext());
        View view = inf.inflate(R.layout.listser, parent, false);
        return new Adapterser.Re(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Re holder, final int position) {
        final  Productservice product = productList.get(position);
        holder.sh.setText(product.getService());
        holder.nu.setText(product.getPrize());
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent=new Intent(context,Editservice.class);

                Log.i("fggfdfg","dfasdfs=============="+product.getId());

                String uid= String.valueOf(product.getId());

                // intent.putExtra("uids",getDataAdapter1.getUid());

                intent.putExtra("name",product.getService());
                intent.putExtra("email",product.getPrize());
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
                        Log.i("sdsdsf", "getid=======" + product.getId());

                        deleteClient(product.getId(), position);
                        de.dismiss();

                        // final GetDataAdapterClient getDataAdapter1 =  getDataAdapter.get(position);

                    }


                });
            }
        });


    }
    public void Filter(ArrayList<Productservice> newList)
    {

        productList=new ArrayList<>();
        productList.addAll(newList);
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return productList.size();
    }
    public static class Re extends RecyclerView.ViewHolder {

        TextView sh, nu;
        Button edit,delete;

        public Re(View itemView) {
                super(itemView);

                sh = itemView.findViewById(R.id.sh);
                nu = itemView.findViewById(R.id.nu);
                edit=itemView.findViewById(R.id.edit);
                delete=itemView.findViewById(R.id.delete);



            }

        }
        public void deleteClient(int uid, final int pos)
        {
            String url="http://brilltechno.com/salon/android/deleteservicedata.php?id="+uid+"";

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
            productList.remove(p0);
            notifyItemRemoved(p0);
            notifyItemRangeChanged(p0, productList.size());


        }


    }
