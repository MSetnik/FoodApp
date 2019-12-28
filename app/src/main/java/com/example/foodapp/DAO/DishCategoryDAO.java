package com.example.foodapp.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.foodapp.Entity.DishCategory;

import java.util.List;

@Dao
public interface DishCategoryDAO {
    @Insert
    void Insert(DishCategory dish);

    @Update
    void Update (DishCategory dish);

    @Delete
    void Delete (DishCategory dish);

    @Query("SELECT * FROM dishCategory_table")
    List<DishCategory> GetAllDishCategory();
}
