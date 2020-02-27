package com.example.foodapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.foodapp.Adapter.DishCategoryAdapter;
import com.example.foodapp.Entity.DishCategory;
import com.example.foodapp.FoodAppViewModel;
import com.example.foodapp.R;

import java.util.List;

public class RestaurantMenuActivity extends AppCompatActivity {
    private static String TAG = "foodapp";
    private FoodAppViewModel viewModel;
    int restaurantID;
    private DishCategoryAdapter DishCategoryAdapter;
    private Button btnWishlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_menu);


        btnWishlist = findViewById(R.id.buttonWishlist);

        btnWishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RestaurantMenuActivity.this, WishlistActivity.class);
                startActivity(intent);
            }
        });

        GetRestaurantCategories();
        RecyclerViewBind();

        viewModel = ViewModelProviders.of(this).get(FoodAppViewModel.class);
        viewModel.GetRestaurantCategory(restaurantID).observe(this, new Observer<List<DishCategory>>() {
            @Override
            public void onChanged(List<DishCategory> dishCategories) {
                DishCategoryAdapter.setlCategories(dishCategories);
            }
        });
        CategoryListener();
    }

    private void GetRestaurantCategories()
    {

        Bundle bundle = getIntent().getExtras();
        restaurantID = bundle.getInt("restaurant_id");
        Log.d(TAG, "onCreate: " + restaurantID);


    }

    private void RecyclerViewBind()
    {
        RecyclerView recyclerView = findViewById(R.id.recycler_view_restaurantMenu);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DishCategoryAdapter = new DishCategoryAdapter();
        recyclerView.setAdapter(DishCategoryAdapter);
    }

    private void CategoryListener()
    {
        DishCategoryAdapter.SetOnCategoryListener(new DishCategoryAdapter.OnCategoryListener() {
            @Override
            public void onCategoryClick(DishCategory dishCategory) {
                Intent intent = new Intent(RestaurantMenuActivity.this, RestaurantCategoryDishActivity.class);
                intent.putExtra("restaurant_id", restaurantID);
                intent.putExtra("category_id", dishCategory.getCategoryID());
                startActivity(intent);
            }
        });
    }

}
