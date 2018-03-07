package com.example.su.restaurants;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList <RestaurantSetGet> arr=new ArrayList<>();
    RequestQueue queue;
    RecyclerView rv;
    RecyclerView.Adapter adapter;
////////////////////
////indiagdgdggrgrederer
    /////////////////////////////testhello
    ////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv=(RecyclerView)findViewById(R.id.rview);
        final RecyclerView.LayoutManager lm=new LinearLayoutManager(this);
        rv.setLayoutManager(lm);
        queue= Volley.newRequestQueue(this);
        fetchJSONResponse(0);
    }



public void fetchJSONResponse(int page_no){
       String url="https://esolz.co.in/lab3/aviina/index.php/Dish/myrestaurantlist?page="+page_no+"&limit=3&cid=2&lat=22.5726&lng=88.3639&distance=&type=0";


       Log.v("url",url);
        JsonObjectRequest req=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(response.getBoolean("status")){
                    JSONArray resList=response.getJSONArray("restaurant");
                    for(int i=0;i<resList.length();i++) {
                        JSONObject obj = resList.getJSONObject(i);
                        String id = obj.getString("id");
                        Log.v("id", id);
                        String image = obj.getString("image");
                        String name = obj.getString("name");
                        Log.v("resname", name);
                        String ratings = obj.getString("rating");
                        String address = obj.getString("address");
                        String food_type = obj.getString("food_type");
                        String distance = obj.getString("distance");
                        String openstatus = obj.getString("openstatus");
                        RestaurantSetGet restaurantSetGet = new RestaurantSetGet();
                        restaurantSetGet.setImage(image);
                        restaurantSetGet.setName(name);
                        restaurantSetGet.setRatings(ratings);
                        restaurantSetGet.setAddress(address);
                        restaurantSetGet.setFood_type(food_type);
                        restaurantSetGet.setDistance(distance);
                        restaurantSetGet.setOpen_status(openstatus);

                        arr.add(restaurantSetGet);
                        if(adapter==null) {
                            adapter = new MyAdapter(getApplicationContext(), arr,MainActivity.this);
                            rv.setAdapter(adapter);
                        }
                        else{
                            adapter.notifyDataSetChanged();
                        }

                    }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
       queue.add(req);

    }



}
