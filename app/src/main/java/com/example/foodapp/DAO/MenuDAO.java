package com.example.foodapp.DAO;

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
    void Insert(Menu dish);

    @Update
    void Update (Menu dish);

    @Delete
    void Delete (Menu dish);

    @Query("SELECT * FROM menu_table")
    List<Menu> GetAllMenu();
}
