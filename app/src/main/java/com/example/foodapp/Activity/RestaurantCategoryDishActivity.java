package com.example.foodapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.foodapp.Adapter.DishAdapter;
import com.example.foodapp.Adapter.DishCategoryAdapter;
import com.example.foodapp.Entity.Dish;
import com.example.foodapp.Entity.DishCategory;
import com.example.foodapp.Entity.Restaurant;
import com.example.foodapp.Entity.Wishlist;
import com.example.foodapp.FoodAppViewModel;
import com.example.foodapp.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class RestaurantCategoryDishActivity extends AppCompatActivity {
    private DishAdapter dishAdapter;
    private Integer restaurantID;
    private Integer categoryID;
    private FoodAppViewModel viewModel;
    private static String TAG = "foodapp";
    private int DishRestaurantID;
    private Button btnWishlist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_category_dish);
        viewModel = ViewModelProviders.of(this).get(FoodAppViewModel.class);

        GetRestaurantID();
        RecyclerViewBind();
        GetRestaurantCategoryDish();

        WishlistClick();
        GetWishlist();
    }


    private void WishlistClick()
    {
        btnWishlist = findViewById(R.id.buttonWishlist);

        btnWishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RestaurantCategoryDishActivity.this, WishlistActivity.class);
                startActivity(intent);
            }
        });
    }


    private void GetRestaurantCategoryDish()
    {
        viewModel.GetRestaurantCategoryDish(restaurantID, categoryID).observe(this, new Observer<List<Dish>>() {
            @Override
            public void onChanged(List<Dish> dishes) {
                dishAdapter.setlDish(dishes);
                Log.d(TAG, "onChanged: " + dishes.size());
            }
        });

    }


    private void GetRestaurantID()
    {
        Bundle bundle = getIntent().getExtras();
        restaurantID = bundle.getInt("restaurant_id");
        categoryID = bundle.getInt("category_id");
        Log.d(TAG, "GetRestaurantID: " + restaurantID + " " + categoryID);

    }

    private void RecyclerViewBind()
    {
        RecyclerView recyclerView = findViewById(R.id.restaurantCategoryDishRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dishAdapter = new DishAdapter();
        recyclerView.setAdapter(dishAdapter);
    }

    private void GetWishlist()
    {
        dishAdapter.SetOnButtonListener(new DishAdapter.OnButtonDishListener() {
            @Override
            public void onButtonClick(final Dish dish) {
                DishRestaurantID = dish.getRestaurantID();
                GetRestaurantFromWishlist(dish);
                Toast.makeText(RestaurantCategoryDishActivity.this, "Jelo Dodano u Wishlistu", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void GetRestaurantFromWishlist(final Dish dish)
    {
        viewModel.GetRestaurantFromWishlist(DishRestaurantID).observe(this, new Observer<List<Restaurant>>() {
            @Override
            public void onChanged(List<Restaurant> restaurants) {

                for (int i=0;i<restaurants.size();i++)
                {
                    Log.d(TAG, "onChanged: " + restaurants.get(i).getRestaurantName() + " " + dish.getDishName());
                    Wishlist wishlist = new Wishlist(restaurants.get(i).getRestaurantId(), restaurants.get(i).getRestaurantName(), dish.getDishID(), dish.getDishName(), dish.getPrice(), dish.getDishDescription());
                    viewModel.AddToWishlist(wishlist);
                }
            }
        });
    }

}
