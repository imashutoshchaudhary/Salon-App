package com.example.sampat.brillsalon;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.android.volley.RequestQueue;

import java.util.ArrayList;

public class Adaptersend extends RecyclerView.Adapter<Adaptersend.Re> {
    RequestQueue requestQueue;
    private ArrayList<Productmsgsend> productList;
    private Context context;

    public Adaptersend(Context context, ArrayList<Productmsgsend> productlist) {
        this.context = context;
        this.productList = productlist;
    }

    @Override
    public Adaptersend.Re onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inf = LayoutInflater.from(parent.getContext());
        final View view = inf.inflate(R.layout.listsend, parent, false);
        return new Adaptersend.Re(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adaptersend.Re holder, int position) {
        final Productmsgsend product = productList.get(position);
        holder.sh.setText(product.getEname());
        holder.nu.setText(product.getMobile());
                product.setChec("0");

        holder.check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                if(isChecked==true)
                {


                    product.setChec("1");
                }
                else
                {
                    product.setChec("0");
                }


            }
        });



    }

    public void setFilter(ArrayList<Productmsgsend>newList)
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

        TextView  nu,sh;
        CheckBox check;


        public Re(View itemView) {
            super(itemView);
             check=itemView.findViewById(R.id.name);
            sh= itemView.findViewById(R.id.sh);
            nu = itemView.findViewById(R.id.number);


        }


    }
}