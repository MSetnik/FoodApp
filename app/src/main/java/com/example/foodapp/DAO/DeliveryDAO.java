package com.example.foodapp.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.foodapp.Entity.Delivery;

import java.util.List;

@Dao
public interface DeliveryDAO {
    @Insert
    void Insert(Delivery dish);

    @Update
    void Update (Delivery dish);

    @Delete
    void Delete (Delivery dish);

    @Query("SELECT * FROM delivery_table")
    List<Delivery> GetAllDelivery();
}
