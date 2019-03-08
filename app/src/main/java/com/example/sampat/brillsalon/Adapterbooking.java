package com.example.sampat.brillsalon;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class Adapterbooking extends RecyclerView.Adapter<Adapterbooking.Re> {
private String[] daata;
    private String[] daata1;
    private String[] daata2;
    private String[] daata3;
    private String[] daata4;
    private int[] daata5;

public Adapterbooking(String[] daata,String[] daata1,String[] daata2,String[] daata3,String[] daata4,int[]daata5) {
        this.daata = daata;
    this.daata1 = daata1;

    this.daata2= daata2;
    this.daata3= daata3;
    this.daata4= daata4;
    this.daata5=daata5;

}
@Override
public Adapterbooking.Re onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inf = LayoutInflater.from(parent.getContext());
        View view = inf.inflate(R.layout.listbooking, parent, false);
        return new Adapterbooking.Re(view);
        }

    @Override
    public void onBindViewHolder(@NonNull Re holder, int position) {
       String t=daata[position];
       holder.name.setText(t);
        String t1=daata1[position];
        holder.number.setText(t1);
        String t2=daata2[position];
        holder.date.setText(t2);
        String t3=daata3[position];
        holder.time.setText(t3);
        String t4=daata4[position];
        holder.rating.setText(t4);
        int t5=daata5[position];
        holder.pic.setImageResource(t5);

    }


@Override
public int getItemCount() {
        return daata.length;
        }
public static class Re extends RecyclerView.ViewHolder {
   TextView name,number,date,time,rating;
   CircleImageView pic;
    public Re(View itemView) {
        super(itemView);
        name=itemView.findViewById(R.id.name);
        number=itemView.findViewById(R.id.number);
        date=itemView.findViewById(R.id.date);
        time=itemView.findViewById(R.id.time);
        rating=itemView.findViewById(R.id.rating);
        pic=itemView.findViewById(R.id.pic);



    }
}
}

