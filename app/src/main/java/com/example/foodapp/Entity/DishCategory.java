package com.example.foodapp.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "dishCategory_table")
public class DishCategory {
    @PrimaryKey(autoGenerate = true)
    private int categoryID;

    private String categoryName;

    public DishCategory(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
