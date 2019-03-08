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

public class Adapterdaily2 extends RecyclerView.Adapter<Adapterdaily2.Re> {
    private ArrayList<Productdialyexp> productList;
    private Context context;

    public Adapterdaily2(Context context,ArrayList<Productdialyexp> productList) {
        this.context=context;
        this.productList =productList;
    }
    @Override
    public Adapterdaily2.Re onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inf = LayoutInflater.from(parent.getContext());
        View view = inf.inflate(R.layout.listdaily2, parent, false);
        return new Adapterdaily2.Re(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapterdaily2.Re holder, int position) {
        final  Productdialyexp product = productList.get(position);
        holder.sha.setText(product.getDate());
        holder.nu.setText(product.getService());
        holder.delete.setText(product.getPrize());

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
    public static class Re extends RecyclerView.ViewHolder {
        ImageView img;
        TextView sha, nu,delete;

        public Re(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            delete=itemView.findViewById(R.id.delete);
            sha = itemView.findViewById(R.id.shaa);
            nu = itemView.findViewById(R.id.nu);




        }
    }
}
