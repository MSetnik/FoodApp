package com.example.foodapp.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.foodapp.Entity.Wishlist;

import java.util.List;

@Dao
public interface WishlistDAO {
    @Insert
    void Insert (Wishlist... wishlist);

    @Update
    void Update(Wishlist... wishlist);

    @Delete
    void Delete(Wishlist... wishlist);

    @Query("SELECT * FROM wishlist_table")
    LiveData<List<Wishlist>>GetAllFromWishlist();

    @Query("SELECT * FROM wishlist_table WHERE restaurantID = :restaurantID")
    LiveData<List<Wishlist>>GetRestaurantWishlist(int restaurantID);

    @Query("DELETE FROM wishlist_table")
    void DeleteWishlist();
}
