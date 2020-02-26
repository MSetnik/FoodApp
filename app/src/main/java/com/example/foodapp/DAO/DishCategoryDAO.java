package com.example.foodapp.DAO;

import androidx.lifecycle.LiveData;
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
    void Insert(DishCategory dishCategory);

    @Update
    void Update (DishCategory dishCategory);

    @Delete
    void Delete (DishCategory dishCategory);

    @Query("SELECT * FROM dishCategory_table")
    LiveData<List<DishCategory>> GetAllDishCategory();

    @Query("SELECT dishCategory_table.* FROM dishCategory_table, menu_table WHERE menu_table.restaurantID == :restaurantID AND menu_table.categoryID == dishCategory_table.categoryID")
    LiveData<List<DishCategory>>GetRestaurantCategory(int restaurantID);
}
