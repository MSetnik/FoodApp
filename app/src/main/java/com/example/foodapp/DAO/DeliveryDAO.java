package com.example.foodapp.DAO;

import androidx.lifecycle.LiveData;
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
    void Insert(Delivery delivery);

    @Update
    void Update (Delivery delivery);

    @Delete
    void Delete (Delivery delivery);

    @Query("SELECT * FROM delivery_table")
    LiveData<List<Delivery>> GetAllDelivery();
}
