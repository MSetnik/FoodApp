package com.example.foodapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import java.util.concurrent.locks.ReentrantLock;

public  class WishlistActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private WishlistAdapter wishlistAdapter;
    private FoodAppViewModel viewmodel;
    private List<Restaurant>lRestaurants = new ArrayList<>();
    private RestaurantWishlist restaurantWishlist;
    private List<RestaurantWishlist> lRestaurantWishlist = new ArrayList<>();
    private List<List<Wishlist>> lRestWishlist = new ArrayList<>();
    private Button btnWishlist;

    private static String TAG ="foodapp";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);

        btnWishlist = findViewById(R.id.buttonWishlist);
        btnWishlist.setVisibility(View.GONE);

        viewmodel = ViewModelProviders.of(this).get(FoodAppViewModel.class);
        RecyclerViewBind();
        GetAllRestaurants();

    }


    private List<Restaurant> GetAllRestaurants(){
        viewmodel.getAllRestaurants().observe(this, new Observer<List<Restaurant>>() {
            @Override
            public void onChanged(List<Restaurant> restaurants) {
                //wishlistAdapter.setlRestaurants(restaurants);
                lRestaurants = restaurants;
                GetRestaurantID();
            }
        });
        return lRestaurants;
    }

    private void GetRestaurantID()
    {
        for(int i=0;i<lRestaurants.size();i++)
        {
            Log.d(TAG, "GetRestaurantID: " + lRestaurants.get(i).getRestaurantName());
            GetRestaurantWishlistFromID(lRestaurants.get(i).getRestaurantId());
        }
    }

    private void GetRestaurantWishlistFromID(final int restaurantID)
    {
        viewmodel.GetRestaurantWishlist(restaurantID);

        viewmodel.SetOnFinishListener(new FoodAppViewModel.OnWishlistFinishListenerVM() {
            @Override
            public void OnFinish(List<Wishlist> wishlist) {
                lRestWishlist.add(wishlist);
                if(lRestaurants.size() == lRestWishlist.size()){
                    for ( int i=0;i<lRestaurants.size();i++ ){
                        RestaurantWishlist restaurantWishlist = new RestaurantWishlist(lRestaurants.get(i).getRestaurantId(), lRestaurants.get(i).getRestaurantName(), lRestaurants.get(i).getPhone(), lRestaurants.get(i).getDelivery(), lRestWishlist.get(i));
                        lRestaurantWishlist.add(restaurantWishlist);
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            wishlistAdapter.setlWishlist(lRestaurantWishlist);
                            CallRestaurant();
                        }
                    });
                }
            }
        });


    }




    private void RecyclerViewBind()
    {
        recyclerView = findViewById(R.id.recyclerview_wishlist);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        wishlistAdapter = new WishlistAdapter(getApplicationContext(), viewmodel);
        recyclerView.setAdapter(wishlistAdapter);

        wishlistAdapter.SetOnClickListenerVanjski(new WishlistAdapter.OnButtonClickListenerVanjski() {
            @Override
            public void OnBtnClickRemove(Wishlist wishlist) {
                viewmodel.DeleteSelectedItem(wishlist);
                Toast.makeText(WishlistActivity.this, "Jelo obrisano", Toast.LENGTH_SHORT).show();
                lRestWishlist.clear();
                lRestaurantWishlist.clear();
                GetRestaurantID();
            }
        });
    }

    private void CallRestaurant()
    {
        wishlistAdapter.SetOnClickListenerPhone(new WishlistAdapter.CallRestaurantListener() {
            @Override
            public void OnPhoneClick(String tel) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + tel));
                startActivity(intent);
            }
        });

    }

}