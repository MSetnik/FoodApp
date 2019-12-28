package com.example.foodapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.foodapp.Entity.Dish;
import com.example.foodapp.Entity.Restaurant;

import java.util.List;

public class FoodAppViewModel extends AndroidViewModel {
    private Repository repository;
    private LiveData<List<Restaurant>> allRestaurants;
    private List<Dish> allDish;

    //Context is Application instead context because view model can outlive activity (memory leak)
    public FoodAppViewModel(@NonNull Application application) {
        super(application);
          repository = new Repository(application);
        allRestaurants = repository.GetAllRestaurants();
        //allDish = repository.GetDish();
    }

    public LiveData<List<Restaurant>> getAllRestaurants(){
        return allRestaurants;
    }

   /* public List<Dish> getAllDish(){
        return allDish;
    }*/
}
