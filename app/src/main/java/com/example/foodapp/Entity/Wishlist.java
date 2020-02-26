package com.example.foodapp.Entity;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "wishlist_table")
public class Wishlist {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int restaurantID;
    private String restaurantName;
    private int dishID;
    private String dishName;
    private double dishPrice;
    private String dishDescription;

    public Wishlist(int restaurantID, String restaurantName, int dishID, String dishName, double dishPrice, String dishDescription) {
        this.restaurantID = restaurantID;
        this.restaurantName = restaurantName;
        this.dishID = dishID;
        this.dishName = dishName;
        this.dishPrice = dishPrice;
        this.dishDescription = dishDescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(int restaurantID) {
        this.restaurantID = restaurantID;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public int getDishID() {
        return dishID;
    }

    public void setDishID(int dishID) {
        this.dishID = dishID;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public double getDishPrice() {
        return dishPrice;
    }

    public void setDishPrice(double dishPrice) {
        this.dishPrice = dishPrice;
    }

    public String getDishDescription() {
        return dishDescription;
    }

    public void setDishDescription(String dishDescription) {
        this.dishDescription = dishDescription;
    }
}
