package com.example.foodapp.Adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.Entity.Restaurant;
import com.example.foodapp.R;

import java.util.ArrayList;
import java.util.List;

public class RestaurantsAdapter extends RecyclerView.Adapter<RestaurantsAdapter.RestaurantsViewHolder> {
    private List<Restaurant> restaurants = new ArrayList<>();
    private OnRestaurantListener restaurantListener;

    @NonNull
    @Override
    public RestaurantsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurants,parent,false);
        RestaurantsViewHolder holder = new RestaurantsViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantsViewHolder holder, int position) {
        Restaurant restaurant = restaurants.get(position);

        String Dostava = "Dostava";
        if(restaurant.getDelivery() == 1)
        {
            holder.delivery.setTextColor(Color.GREEN);
        }
        else{
            holder.delivery.setTextColor(Color.RED);
        }
        holder.restaurantNameTW.setText(restaurant.getRestaurantName());
        holder.restaurantAddressTW.setText(restaurant.getAddress());
        holder.restaurantPhoneTW.setText(restaurant.getPhone());
        holder.delivery.setText(Dostava);
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    public void setRestaurants(List<Restaurant> restaurant) {
        this.restaurants = restaurant;
        notifyDataSetChanged();
    }

    class RestaurantsViewHolder extends RecyclerView.ViewHolder{
        private TextView restaurantNameTW;
        private TextView restaurantAddressTW;
        private TextView restaurantPhoneTW;
        private TextView delivery;

        public RestaurantsViewHolder(@NonNull View itemView) {
            super(itemView);

            restaurantNameTW = itemView.findViewById(R.id.restaurantName);
            restaurantAddressTW = itemView.findViewById(R.id.restaurantAddress);
            restaurantPhoneTW = itemView.findViewById(R.id.restaurantPhone);
            delivery = itemView.findViewById(R.id.restaurantDelivery);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    restaurantListener.OnRestaurantClick(restaurants.get(position));
                }
            });
        }
    }

    public interface OnRestaurantListener
    {
        void OnRestaurantClick(Restaurant restaurant);
    }

    public void SetOnRestaurantListener(OnRestaurantListener onRestaurantListener)
    {
        this.restaurantListener = onRestaurantListener;
    }
}
