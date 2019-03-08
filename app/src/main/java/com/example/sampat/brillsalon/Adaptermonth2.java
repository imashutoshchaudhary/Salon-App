package com.example.sampat.brillsalon;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class Adaptermonth2 extends RecyclerView.Adapter<Adaptermonth2.Re> {
    private ArrayList<Productmonth2> productList;
    private Context context;

    public Adaptermonth2(Context context, ArrayList<Productmonth2> productlist) {
        this.context = context;
        this.productList = productlist;
    }

    @Override
    public Adaptermonth2.Re onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inf = LayoutInflater.from(parent.getContext());
        View view = inf.inflate(R.layout.listmonth2, parent, false);
        return new Adaptermonth2.Re(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Re holder, int position) {
        final Productmonth2 product = productList.get(position);
        holder.delete.setText(product.getEx());
        holder.monthly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent(context, Weekly.class);
                context.startActivity(p);
            }
        });

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class Re extends RecyclerView.ViewHolder {
        ImageView img;
         TextView  delete;
         LinearLayout monthly;

        public Re(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            delete = itemView.findViewById(R.id.delete);
            monthly = itemView.findViewById(R.id.monthly);


        }


    }
}


