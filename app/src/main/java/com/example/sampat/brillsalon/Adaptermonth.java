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

public class Adaptermonth extends RecyclerView.Adapter<Adaptermonth.Re> {
    private ArrayList<Productmonthbal> productList;
    private Context context;

    public Adaptermonth(Context context, ArrayList<Productmonthbal> productlist) {
        this.context = context;
        this.productList = productlist;
    }

    @Override
    public Adaptermonth.Re onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inf = LayoutInflater.from(parent.getContext());
        View view = inf.inflate(R.layout.listmonth, parent, false);
        return new Adaptermonth.Re(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Re holder, int position) {
        final Productmonthbal product = productList.get(position);
        holder.nu.setText(product.getMonth());
        holder.edit.setText(product.getIncome());
        holder.monthly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent(context, Weekly.class);
                String uid= String.valueOf(product.getId());

                // intent.putExtra("uids",getDataAdapter1.getUid());

                p.putExtra("name",product.getMonth());
                p.putExtra("email",product.getIncome());
                p.putExtra("uids",uid);

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
        public static TextView nu, edit, delete;
        LinearLayout monthly;

        public Re(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            nu = itemView.findViewById(R.id.nu);
            edit = itemView.findViewById(R.id.edit);
            delete = itemView.findViewById(R.id.delete);
            monthly = itemView.findViewById(R.id.monthly);


        }


    }
}


