package com.example.sampat.brillsalon;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapterweeklyexp extends RecyclerView.Adapter<Adapterweeklyexp.Re> {
    private ArrayList<Productweeklyexp> productweeklyexps;
    private Context context;
    public Adapterweeklyexp(Context context ,ArrayList<Productweeklyexp> productweeklyexps) {
        this.context=context;
        this.productweeklyexps=productweeklyexps;
    }


    @NonNull
    @Override
    public Adapterweeklyexp.Re onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inf = LayoutInflater.from(parent.getContext());
        View view = inf.inflate(R.layout.listweek, parent, false);
        return new Adapterweeklyexp.Re(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapterweeklyexp.Re holder, int position) {
        final Productweeklyexp p=productweeklyexps.get(position);
        holder.delete.setText(p.getExp());
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
        return productweeklyexps.size();
    }
    public static class Re extends RecyclerView.ViewHolder {
        TextView num,edit,delete;
        LinearLayout weekly;

        public Re(View itemView) {
            super(itemView);
            num = itemView.findViewById(R.id.num);
            edit=itemView.findViewById(R.id.edit);
            delete=itemView.findViewById(R.id.delete);
            weekly=itemView.findViewById(R.id.weekly);


        }
    }

}
