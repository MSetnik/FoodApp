package com.example.foodapp.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.foodapp.Entity.Dish;
import com.example.foodapp.Entity.Restaurant;

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
    List<Dish>GetAllDishes();
}
