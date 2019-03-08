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

public class Adapterweekly extends RecyclerView.Adapter<Adapterweekly.Re> {
    private ArrayList<Productweeklyin> productList;
    private Context context;
    public Adapterweekly(Context context,ArrayList<Productweeklyin> productlist ) {
        this.context=context;
        this.productList = productlist;
    }


    @Override
    public Adapterweekly.Re onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inf = LayoutInflater.from(parent.getContext());
        View view = inf.inflate(R.layout.listweekly, parent, false);
        return new Adapterweekly.Re(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Re holder, int position) {
        final  Productweeklyin product = productList.get(position);
        holder.num.setText(product.getDate());
        holder.edit.setText(product.getPrice());
        holder.weekly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent k=new Intent(context,Weeklyinex.class);
               context.startActivity(k);
            }
        });

    }




    @Override
    public int getItemCount() {
        return productList.size();
    }



    public static class Re extends RecyclerView.ViewHolder {
        ImageView img;
        TextView  num,edit;
        LinearLayout weekly;

        public Re(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
           num = itemView.findViewById(R.id.num);
           weekly=itemView.findViewById(R.id.weekly);
           edit=itemView.findViewById(R.id.edit);


        }
    }
}
