package com.example.sampat.brillsalon;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class Adaptertemp extends RecyclerView.Adapter<Adaptertemp.Re>{
    private String ds[];
    private  String s[];
    public Adaptertemp(String ds[],String s[]){
        this.ds=ds;this.s=s;
    }
    @Override
    public Adaptertemp.Re onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inf = LayoutInflater.from(parent.getContext());
        View view = inf.inflate(R.layout.listmssg, parent, false);
        return new Adaptertemp.Re(view);
        }

    @Override
    public void onBindViewHolder(@NonNull Adaptertemp.Re holder, int position) {
        String title=ds[position];
        String fd=s[position];
        holder.brt.setText(title);
        holder.hbd.setText(fd);

    }



    @Override
    public int getItemCount() {
        return ds.length;
    }
    public static class Re extends RecyclerView.ViewHolder {
        ImageView img;
        TextView brt, hbd;

        public Re(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            brt = itemView.findViewById(R.id.brt);
            hbd = itemView.findViewById(R.id.hbd);


        }
    }
}
