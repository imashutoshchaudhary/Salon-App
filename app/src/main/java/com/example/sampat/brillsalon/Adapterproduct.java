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

public class Adapterproduct extends RecyclerView.Adapter<Adapterproduct.Re> {
    RequestQueue requestQueue;
    private ArrayList<Productslod> productList;
    private Context context;
    public Adapterproduct(Context context,ArrayList<Productslod> productlist ) {
        this.context=context;
        this.productList = productlist;
    }
    @Override
    public Adapterproduct.Re onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inf = LayoutInflater.from(parent.getContext());
        View view = inf.inflate(R.layout.listproduct, parent, false);
        return new Adapterproduct.Re(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapterproduct.Re holder, final int position) {
        final  Productslod product = productList.get(position);
        holder.sh.setText(product.getName());
        holder.nu.setText(product.getPrice());
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent=new Intent(context,Editproduct.class);

                Log.i("fggfdfg","dfasdfs=============="+product.getId());

                String uid= String.valueOf(product.getId());

                // intent.putExtra("uids",getDataAdapter1.getUid());

                intent.putExtra("name",product.getName());
                intent.putExtra("email",product.getPrice());
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
    public void Filter(ArrayList<Productslod> newList)
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

        TextView sh,nu,edit,delete;
        public Re(View itemView) {
            super(itemView);
            sh = itemView.findViewById(R.id.sh);
            nu= itemView.findViewById(R.id.nu);
            edit=itemView.findViewById(R.id.edit);
            delete=itemView.findViewById(R.id.delete);
        }
}
    public void deleteClient(int uid, final int pos)
    {
        String url="http://brilltechno.com//salon/android/deleteproduct.php?id="+uid+"";

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
