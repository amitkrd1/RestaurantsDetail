package com.example.su.restaurants;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by su on 6/10/17.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context context;
    ArrayList <RestaurantSetGet> alist;
    MainActivity activity;
    int page_no=0;



    public MyAdapter(Context context, ArrayList<RestaurantSetGet> alist,MainActivity myactivity) {

        this.context = context;
        this.alist = alist;
        this.activity=myactivity;
    }




    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View v=inflater.inflate(R.layout.restaurant,parent,false);
        MyViewHolder vhold=new MyViewHolder(v);

        return vhold;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {



            Log.d("position--",""+position);
            RestaurantSetGet sg = alist.get(position);
            if (!sg.getImage().equals(""))
                Picasso.with(context).load(sg.getImage()).into(holder.resimage);
            holder.resname.setText(alist.get(position).getName());
            holder.location.setText(alist.get(position).getAddress());
            holder.foodtype.setText(alist.get(position).getFood_type());
            holder.distance.setText(alist.get(position).getDistance());
            if (sg.getOpen_status().equals("false")) {
                holder.openstatus.setText("closed");
            } else {
                holder.openstatus.setText("open");
            }

            holder.rating.setText(alist.get(position).getRatings());

        if(alist.size()-1==position){
            page_no++;
           // ((MainActivity)context).fetchJSONResponse(page_no);
            activity.fetchJSONResponse(page_no);
        }


    }

    @Override
    public int getItemCount() {

        Log.v("size",String.valueOf(alist.size()));
        return alist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView resimage,locationicon,ratingicon;
        TextView resname, rating,location,foodtype,openstatus,distance;



        public MyViewHolder(View itemView) {

            super(itemView);
            resimage=(ImageView)itemView.findViewById(R.id.iv_res);
            resname=(TextView)itemView.findViewById(R.id.res_name);
            rating=(TextView)itemView.findViewById(R.id.tv_rating);
            location=(TextView)itemView.findViewById(R.id.tv_location);
            foodtype=(TextView)itemView.findViewById(R.id.tv_food_name);
            openstatus=(TextView)itemView.findViewById(R.id.openstatus);
            distance=(TextView)itemView.findViewById(R.id.tv_distance);
        }
    }
}
