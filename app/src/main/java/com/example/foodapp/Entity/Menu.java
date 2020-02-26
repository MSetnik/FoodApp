package com.example.foodapp.Entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "menu_table")
public class Menu {
    @PrimaryKey(autoGenerate = true)
    private int menuID;
    private int restaurantID;
    private int categoryID;

    public Menu(int restaurantID, int categoryID) {
        this.restaurantID = restaurantID;
        this.categoryID = categoryID;
    }

    public void setMenuID(int menuID) {
        this.menuID = menuID;
    }

    public int getMenuID() {
        return menuID;
    }

    public int getRestaurantID() {
        return restaurantID;
    }

    public int getCategoryID() {
        return categoryID;
    }
}
