package com.example.sampat.brillsalon;

import android.graphics.Bitmap;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class Adapterupload extends RecyclerView.Adapter<Adapterupload.Re> {
  private int []q;
  private int []a;
  public Adapterupload(int []q,int []a){
      this.q=q;
      this.a=a;
  }
    @Override
    public Adapterupload.Re onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inf = LayoutInflater.from(parent.getContext());
        View view = inf.inflate(R.layout.listupload, parent, false);
        return new Adapterupload.Re(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapterupload.Re holder, int position) {
      int title=q[position];
      holder.img1.setImageResource(title);

        int title1=a[position];
        holder.img2.setImageResource(title1);

    }

    @Override
    public int getItemCount() {
        return q.length;
    }

    public static class Re extends RecyclerView.ViewHolder {
        ImageView img1,img2;


        public Re(View itemView) {
            super(itemView);
            img1 = itemView.findViewById(R.id.img1);
            img2= itemView.findViewById(R.id.img2);



        }
    }

}
