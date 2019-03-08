package com.example.sampat.brillsalon;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


public class AdapterGetPlatniumPlans extends RecyclerView.Adapter<AdapterGetPlatniumPlans.MyViewHolder> {

 //   SharedPreferences prefrance,prefranceid;
    SharedPreferences prefrance,prefranceid,prefPlans;


    public CircleImageView photouser;

    Boolean isinternetpresent;
    ConnectDetector cd;

    String sharid;


    String prefid="id",prefupid="upid",preftitle="title",prefdate="date",prefuploader="uploader",prefimages="images",prefdetails="details",prefvideo="video";
    private ArrayList<GatterGetPlatinumplans> recyclerModels; // this data structure carries our title and description
    Context context;
    public AdapterGetPlatniumPlans(ArrayList<GatterGetPlatinumplans> recyclerModels, Context context) {
        this.recyclerModels = recyclerModels;
        this.context=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflate your custom row layout here
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_allplansitem, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // update your data here
        final GatterGetPlatinumplans getUserdtl = recyclerModels.get(position);
        //getUserdtl=getAda1;
        String planId ,planname,planduration,planprice,plannew;

        prefranceid=context.getSharedPreferences(Configs.UserPrefrance, Context.MODE_PRIVATE);
        sharid= prefranceid.getString("id","");

        planduration= getUserdtl.getPlanduration();

        planprice= getUserdtl.getPlanprice();
       // char plandurationfchar = planduration.charAt(0);

        Log.i("","planprice=================="+planprice);

        if(planduration.equals("1month"))
        {
          holder.txtDuration.setText("1 Month Package");
            holder.txtPrice.setText("\u20B9"+" "+planprice+"/-");
        }
      else  if(planduration.equals("3month"))
        {
            holder.txtDuration.setText("3 Month Package");
            holder.txtPrice.setText("\u20B9"+" "+planprice+"/-");
        }
      else  if(planduration.equals("6month"))
        {
            holder.txtDuration.setText("6 Month Package");
            holder.txtPrice.setText("\u20B9"+" "+planprice+"/-");
        }
        else
        {


        }


        holder.btnpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                prefPlans=context.getSharedPreferences(Configs.planprice, Context.MODE_PRIVATE);

                String userid,username;
                SharedPreferences.Editor editor = prefPlans.edit();
                editor.putString("planid",getUserdtl.getPlanId());
                editor.putString("planname",getUserdtl.getPlanname());
                editor.putString("planduration",getUserdtl.getPlanduration());
                editor.putString("planprice",getUserdtl.getPlanprice());
                editor.putString("plannew",getUserdtl.getPlannew());
                editor.commit();

                Intent intent=new Intent(context, Activity_GetPlanAndPrice.class);
                context.startActivity(intent);

            }
        });


/*

            holder.setItemClickListener(new ItemClickListener() {
                @Override
                public void onItemClick(int pos) {


                    cd = new ConnectDetector(context);
                    isinternetpresent = cd.isConnectToInternet();

                    if (isinternetpresent) {

                        prefrance = context.getSharedPreferences("selectedUser", Context.MODE_PRIVATE);

                        SharedPreferences.Editor editor = prefrance.edit();

                        editor.commit();




                    } else {
                        Toast.makeText(context, "internet Not Present", Toast.LENGTH_SHORT).show();
                    }


                }
            });
*/




       /* holder.btnviewmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newstitle,newsdate,newsuploader,newsimages,newsdetails,newsid,newsupid,newsvideo,profilestatus;


                preferences=context.getSharedPreferences(Configs.adminSingleNewsDetail, Context.MODE_PRIVATE);

                String userid,username;

                newstitle=getAda1.getTitle();
                newsdate=getAda1.getUp_datetime();
                newsuploader=getAda1.getUp_name();
                newsimages=getAda1.getNews_photo();
                newsdetails=getAda1.getDetails();

                newsid=getAda1.getNewsid();
                newsupid=getAda1.getUp_id();
                newsvideo=getAda1.getNews_video();


                Log.i("","user id is============="+newstitle);
                SharedPreferences.Editor editor=preferences.edit();
                editor.putString(prefid,newsid);
                editor.putString(prefupid,newsupid);
                editor.putString(preftitle,newstitle);
                editor.putString(prefdate,newsdate);
                editor.putString(prefuploader,newsuploader);
                editor.putString(prefimages,newsimages);
                editor.putString(prefdetails,newsdetails);
                editor.putString(prefvideo,newsvideo);

                editor.commit();


                Intent i=new Intent(context, NewsDetails.class);
                context.startActivity(i);


            }
        });
        */

    }



    @Override
    public int getItemCount() {
        return recyclerModels.size();
    }


    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder  {
        // view this our custom row layout, so intialize your variables here


        public TextView txtDuration,txtPrice;
        public CircleImageView photouser,BtnViewProfile;
        public Button btnpay;
        ItemClickListener itemClickListener;




        MyViewHolder(View view) {
            super(view);
            txtDuration =(TextView) itemView.findViewById(R.id.txtDuration);
            txtPrice =(TextView) itemView.findViewById(R.id.txtPrice);
            btnpay =(Button) itemView.findViewById(R.id.btnpay);
          //  itemView.setOnClickListener(this);
        }
       /* public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }
        @Override
        public void onClick(View view) {
            this.itemClickListener.onItemClick(this.getLayoutPosition());
        }*/
    }

    public void filterList(ArrayList<GatterGetPlatinumplans> filterdNames) {
        this.recyclerModels = filterdNames;
        notifyDataSetChanged();
    }

    public GatterGetPlatinumplans getItem(int position) {
        return recyclerModels.get(position);
    }
}
