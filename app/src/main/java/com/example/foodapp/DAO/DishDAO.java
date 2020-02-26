package com.example.foodapp.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.foodapp.Entity.Dish;

import java.util.List;

@Dao
public interface DishDAO {

    @Insert
    void Insert(Dish dish);

    @Update
    void Update (Dish dish);

    @Delete
    void Delete (Dish dish);

    @Query("SELECT * FROM dish_table")
    LiveData<List<Dish>> GetAllDishes();

    @Query("SELECT * FROM dish_table WHERE dish_table.dishCategoryID = :categoryID")
    LiveData<List<Dish>>GetCategoryDishes(int categoryID);

    @Query("SELECT dish_table.* FROM dish_table WHERE dish_table.restaurantID == :restaurantID AND dish_table.dishCategoryID == :categoryID")
    LiveData<List<Dish>>GetRestaurantCategoryDish(int restaurantID, int categoryID);
}
