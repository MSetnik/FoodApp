package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.example.foodapp.Adapter.RestaurantsAdapter;
import com.example.foodapp.DAO.RestaurantDAO;
import com.example.foodapp.Entity.Restaurant;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private FoodAppViewModel viewModel;
    private LiveData<List<Restaurant>> allRestaurants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view_restaurants);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final RestaurantsAdapter rAdapter = new RestaurantsAdapter();
        recyclerView.setAdapter(rAdapter);

        viewModel = ViewModelProviders.of(this).get(FoodAppViewModel.class);
        viewModel.getAllRestaurants().observe(this, new Observer<List<Restaurant>>() {
            @Override
            public void onChanged(List<Restaurant> restaurants) {
                rAdapter.setRestaurants(restaurants);
            }
        });



    }
}
