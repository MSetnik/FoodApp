package com.example.foodapp.Entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "dish_table", foreignKeys = @ForeignKey(entity = DishCategory.class, parentColumns = "categoryID", childColumns = "dishCategoryID", onDelete = CASCADE, onUpdate = CASCADE), indices = {@Index(value = {"dishCategoryID"}, unique = true)})
public class Dish {
    @PrimaryKey(autoGenerate = true)
    private int dishID;

    private String dishName;

    private float price;

    private int dishCategoryID;

    public Dish(String dishName, float price, int dishCategoryID) {
        this.dishName = dishName;
        this.price = price;
        this.dishCategoryID = dishCategoryID;
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

    public float getPrice() {
        return price;
    }

    public int getDishCategoryID() {
        return dishCategoryID;
    }
}
