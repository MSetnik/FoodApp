package com.example.foodapp.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.Adapter.RestaurantsAdapter;
import com.example.foodapp.Entity.Restaurant;
import com.example.foodapp.FoodAppViewModel;
import com.example.foodapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private FoodAppViewModel viewModel;
    private RestaurantsAdapter rAdapter;
    private BottomNavigationView bottomNavigationView;
    private RecyclerView recyclerView;

    private Button btnWishlist;

    private String TAG = "foodapp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = ViewModelProviders.of(this).get(FoodAppViewModel.class);
        RecyclerviewBind();
        WishlistClick();
        BottomNavigationClick();
        GetAllRestaurants();
        onRestaurantClick();
    }

    private void onRestaurantClick()
    {
        rAdapter.SetOnRestaurantListener(new RestaurantsAdapter.OnRestaurantListener() {
            @Override
            public void OnRestaurantClick(Restaurant restaurant) {
                Intent intent = new Intent(MainActivity.this, RestaurantMenuActivity.class);
                intent.putExtra("restaurant_id", restaurant.getRestaurantId());
                startActivity(intent);

            }
        });
    }

    private void GetAllRestaurants()
    {
        viewModel.getAllRestaurants().observe(this, new Observer<List<Restaurant>>() {
            @Override
            public void onChanged(List<Restaurant> restaurants) {
                rAdapter.setRestaurants(restaurants);
                Log.d(TAG, "restaurants: " + restaurants);
            }
        });
    }

    private void BottomNavigationClick()
    {
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.restaurantsTab);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId())
                {
                    case R.id.menuTab:
                        startActivity(new Intent(getApplicationContext(), MenuActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.restaurantsTab:
                        return true;


                }
                return false;
            }
        });
    }

    private void WishlistClick()
    {
        btnWishlist = findViewById(R.id.buttonWishlist);

        btnWishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WishlistActivity.class);
                startActivity(intent);
            }
        });
    }

    private void RecyclerviewBind()
    {
        recyclerView = findViewById(R.id.recycler_view_restaurants);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        rAdapter = new RestaurantsAdapter();
        recyclerView.setAdapter(rAdapter);

    }


    @Override
    protected void onResume() {
        super.onResume();
        bottomNavigationView.setSelectedItemId(R.id.restaurantsTab);
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(MainActivity.this, WishlistActivity.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.DeleteWishlist();
    }

}
