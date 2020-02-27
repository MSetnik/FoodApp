package com.example.foodapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
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
import com.example.foodapp.AppExecutor;
import com.example.foodapp.Entity.Dish;
import com.example.foodapp.Entity.DishCategory;
import com.example.foodapp.Entity.Restaurant;
import com.example.foodapp.Entity.Wishlist;
import com.example.foodapp.FoodAppViewModel;
import com.example.foodapp.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DishActivity extends AppCompatActivity {
    private FoodAppViewModel viewModel;
    private int category_id;
    private DishAdapter dAdapter;
    private RecyclerView recyclerView;
    private static String TAG = "foodapp";
    private int DishRestaurantID;
    private Button btnWishlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish);
        viewModel = ViewModelProviders.of(this).get(FoodAppViewModel.class);

        btnWishlist = findViewById(R.id.buttonWishlist);

        btnWishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DishActivity.this, WishlistActivity.class);
                startActivity(intent);
            }
        });

        RecyclerViewBind();
        GetCategoryID();
        GetDishOfCategory();
        AddToWishlist();

    }

    private void GetCategoryID()
    {
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            category_id = extras.getInt("category_id");
        }
    }

    private void GetDishOfCategory()
    {
        viewModel.getlDishCategory(category_id).observe(this, new Observer<List<Dish>>() {
            @Override
            public void onChanged(List<Dish> dishes) {
                dAdapter.setlDish(dishes);
            }
        });
    }

    private void RecyclerViewBind()
    {
        recyclerView = findViewById(R.id.dishRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        dAdapter = new DishAdapter();
        recyclerView.setAdapter(dAdapter);
    }


    public void AddToWishlist()
    {
        dAdapter.SetOnButtonListener(new DishAdapter.OnButtonDishListener() {
            @Override
            public void onButtonClick(final Dish dish) {
                DishRestaurantID = dish.getRestaurantID();
                GetRestaurantFromWishlist(dish);
                Toast.makeText(DishActivity.this, "Jelo Dodano u Wishlistu", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void GetRestaurantFromWishlist(final Dish dish)
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
