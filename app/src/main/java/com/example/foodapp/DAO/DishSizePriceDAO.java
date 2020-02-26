package com.example.foodapp.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.foodapp.Entity.DishCategory;
import com.example.foodapp.Entity.DishSizePrice;

import java.util.List;

@Dao
public interface DishSizePriceDAO {
    @Insert
    void Insert(DishSizePrice dishSizePrice);

    @Update
    void Update (DishSizePrice dishSizePrice);

    @Delete
    void Delete (DishSizePrice dishSizePrice);

    @Query("SELECT * FROM dishSizePrice_table")
    LiveData<List<DishSizePrice>> GetAllDishSizePrice();
}
