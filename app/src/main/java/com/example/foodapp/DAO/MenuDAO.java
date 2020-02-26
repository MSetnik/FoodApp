package com.example.foodapp.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.foodapp.Entity.Menu;

import java.util.List;

@Dao
public interface MenuDAO {
    @Insert
    void Insert(Menu menu);

    @Update
    void Update (Menu menu);

    @Delete
    void Delete (Menu menu);

    @Query("SELECT * FROM menu_table")
    LiveData<List<Menu>> GetAllMenu();

    @Query("SELECT * FROM menu_table WHERE menu_table.restaurantID = :restaurantID")
    LiveData<List<Menu>>GetRestaurantMenu(int restaurantID);
}
