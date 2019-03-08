package com.example.sampat.brillsalon;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapterdaily extends RecyclerView.Adapter<Adapterdaily.Re> {
    private ArrayList<Productdailybal> productList;
   private Context context;

    public Adapterdaily(Context context, ArrayList<Productdailybal> productlist ) {
        this.context=context;
        this.productList = productlist;
    }
    @Override
    public Adapterdaily.Re onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inf = LayoutInflater.from(parent.getContext());
        View view = inf.inflate(R.layout.listdaily, parent, false);
        return new Adapterdaily.Re(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapterdaily.Re holder, int position) {
        final  Productdailybal product = productList.get(position);
        holder.sha.setText(product.getDate());
        holder.nu.setText(product.getName());
        holder.edit.setText(product.getService());
        holder.delete.setText(product.getPrize());

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
    public static class Re extends RecyclerView.ViewHolder {
        ImageView img;
        TextView sha, nu,edit,delete;

        public Re(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            edit=itemView.findViewById(R.id.edit);
            delete=itemView.findViewById(R.id.delete);
            sha = itemView.findViewById(R.id.shaa);
            nu = itemView.findViewById(R.id.nu);


        }
    }
}
