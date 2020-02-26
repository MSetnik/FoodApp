package com.example.foodapp.Entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "dish_table")
public class Dish {

    @PrimaryKey(autoGenerate = true)
    private int dishID;
    private String dishName;
    private String dishDescription;
    private double price;
    private int dishCategoryID;
    private int restaurantID;

    public Dish(String dishName, String dishDescription, double price, int dishCategoryID, int restaurantID) {
        this.dishName = dishName;
        this.dishDescription = dishDescription;
        this.price = price;
        this.dishCategoryID = dishCategoryID;
        this.restaurantID = restaurantID;
    }

    public void setDishID(int dishID) {
        this.dishID = dishID;
    }

    public int getDishID() {
        return dishID;
    }

    public String getDishName() {
        return dishName;
    }

    public String getDishDescription() {
        return dishDescription;
    }

    public double getPrice() {
        return price;
    }

    public int getDishCategoryID() {
        return dishCategoryID;
    }

    public int getRestaurantID() {
        return restaurantID;
    }
}
