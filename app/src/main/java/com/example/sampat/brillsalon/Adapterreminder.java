package com.example.sampat.brillsalon;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class Adapterreminder extends RecyclerView.Adapter<Adapterreminder.Re> {
    private String []n;
    private String []e;
    private String []p;
    public Adapterreminder(String []n,String []e,String []p){
        this.n=n;
        this.e=e;this.p=p;

    }
    @Override
    public Adapterreminder.Re onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inf = LayoutInflater.from(parent.getContext());
        View view = inf.inflate(R.layout.listreminder, parent, false);
        return new Adapterreminder.Re(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapterreminder.Re holder, int position) {
        String title=n[position];
        holder.na.setText(title);
        String title1=e[position];
        holder.em.setText(title1);
        String title2=p[position];
        holder.ph.setText(title2);


    }

    @Override
    public int getItemCount() {
        return n.length;
    }

    public static class Re extends RecyclerView.ViewHolder {
        TextView na,em,ph;

        public Re(View itemView) {
            super(itemView);

            na = itemView.findViewById(R.id.na);
            em = itemView.findViewById(R.id.em);
            ph=itemView.findViewById(R.id.ph);


        }
    }

}
