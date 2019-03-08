package com.example.sampat.brillsalon;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapterbill extends RecyclerView.Adapter<Adapterbill.Re> {

     ArrayList<Productserviceprice> product;

     Context context;

    public Adapterbill(Context context, ArrayList<Productserviceprice> productlist) {
        this.context = context;
        this.product = productlist;
    }

    @Override
    public Adapterbill.Re onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inf = LayoutInflater.from(parent.getContext());
        final View view = inf.inflate(R.layout.listbill, parent, false);
        return new Adapterbill.Re(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Re holder, int position) {
        final  Productserviceprice product1 = product.get(position);
        holder.sh.setText(product1.getS_name());
        holder.nu.setText(product1.getS_price());

    }

        @Override
        public int getItemCount () {
            return product.size();
    }


        public static class Re extends RecyclerView.ViewHolder {

            TextView sh, nu;

            public Re(View itemView) {
                super(itemView);

                sh = itemView.findViewById(R.id.servicename);
                nu = itemView.findViewById(R.id.price);


            }

        }
    }
