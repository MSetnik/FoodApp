package com.example.foodapp.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.foodapp.Entity.Restaurant;

import java.util.List;

@Dao
public interface RestaurantDAO {

    @Insert
    void Insert (Restaurant... restaurant);

    @Update
    void Update(Restaurant... restaurant);

    @Delete
    void Delete(Restaurant... restaurant);

    @Query("SELECT * FROM restaurant_table")
    LiveData<List<Restaurant>>GetAllRestaurants();

    @Query("SELECT * FROM restaurant_table WHERE restaurantId = :restaurantID")
    LiveData<List<Restaurant>>GetRestaurantFromWishlist(int restaurantID);


}
