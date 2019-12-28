package com.example.foodapp.Entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "menu_table", foreignKeys = {
        @ForeignKey(entity = Restaurant.class,
                    parentColumns = "restaurantId",
                    childColumns = "restaurantID",
                    onDelete = CASCADE,
                    onUpdate = CASCADE),
        @ForeignKey(entity = Dish.class,
                    parentColumns = "dishID",
                    childColumns = "dishID",
                    onDelete = CASCADE,
                    onUpdate = CASCADE)
        }, primaryKeys = {"restaurantID" , "dishID"}, indices = {@Index(value = {"dishID", "restaurantID"}, unique = true)})
public class Menu {
    private int restaurantID;
    private int dishID;

    public Menu(int restaurantID, int dishID) {
        this.restaurantID = restaurantID;
        this.dishID = dishID;
    }

    public int getRestaurantID() {
        return restaurantID;
    }

    public int getDishID() {
        return dishID;
    }
}
