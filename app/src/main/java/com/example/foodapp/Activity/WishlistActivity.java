package com.example.foodapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.example.foodapp.Adapter.WishlistAdapter;
import com.example.foodapp.Adapter.WishlistDishAdapter;
import com.example.foodapp.Entity.Dish;
import com.example.foodapp.Entity.Restaurant;
import com.example.foodapp.Entity.Wishlist;
import com.example.foodapp.FoodAppViewModel;
import com.example.foodapp.Model.RestaurantWishlist;
import com.example.foodapp.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public  class WishlistActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private WishlistAdapter wishlistAdapter;
    private FoodAppViewModel viewmodel;
    private List<Restaurant>lRestaurants = new ArrayList<>();
    private RestaurantWishlist restaurantWishlist;
    private List<RestaurantWishlist> lRestaurantWishlist = new ArrayList<>();

    private static String TAG ="foodapp";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);

        viewmodel = ViewModelProviders.of(this).get(FoodAppViewModel.class);
        RecyclerViewBind();
        GetAllRestaurants();
    }


    public List<Restaurant> GetAllRestaurants(){
        viewmodel.getAllRestaurants().observe(this, new Observer<List<Restaurant>>() {
            @Override
            public void onChanged(List<Restaurant> restaurants) {
                wishlistAdapter.setlRestaurants(restaurants);
                lRestaurants = restaurants;
                GetRestaurantID();
            }
        });
        return lRestaurants;
    }

    public void GetRestaurantID()
    {
        for(int i=0;i<lRestaurants.size();i++)
        {
            Log.d(TAG, "GetRestaurantID: " + lRestaurants.get(i).getRestaurantName());
            GetRestaurantWishlistFromID(lRestaurants.get(i).getRestaurantId());
        }
    }

    public void GetRestaurantWishlistFromID(final int restaurantID)
    {
        viewmodel.GetRestaurantWishlist(restaurantID).observe(this, new Observer<List<Wishlist>>() {
            @Override
            public void onChanged(List<Wishlist> wishlists) {
                for (int i = 0; i < wishlists.size(); i++) {
                    if(restaurantID == wishlists.get(i).getRestaurantID()) {
                        restaurantWishlist = new RestaurantWishlist(wishlists.get(i).getRestaurantID(), wishlists.get(i).getRestaurantName(), wishlists);
                        lRestaurantWishlist.add(restaurantWishlist);
                    }
                }
                wishlistAdapter.setlWishlist(lRestaurantWishlist);
            }

        });
    }


    private void RecyclerViewBind()
    {
        recyclerView = findViewById(R.id.recyclerview_wishlist);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        wishlistAdapter = new WishlistAdapter(getApplicationContext(), viewmodel);
        recyclerView.setAdapter(wishlistAdapter);
    }

}