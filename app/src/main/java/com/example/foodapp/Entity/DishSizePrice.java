package com.example.foodapp.Entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "dishSizePrice_table")
public class DishSizePrice {
    @PrimaryKey(autoGenerate = true)
    private int ID;
    private int dishID;
    private String size;
    private double price;

    public DishSizePrice(int dishID, String size, double price) {
        this.dishID = dishID;
        this.size = size;
        this.price = price;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getDishID() {
        return dishID;
    }

    public String getSize() {
        return size;
    }

    public double getPrice() {
        return price;
    }
}
