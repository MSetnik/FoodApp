package com.example.foodapp.Entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "restaurant_table")
public class Restaurant {

    @PrimaryKey(autoGenerate = true)
    private int restaurantId;

    private String restaurantName;

    private String phone;

    private String address;

    private int delivery;

    public Restaurant(String restaurantName, String phone, String address, int delivery) {
        this.restaurantName = restaurantName;
        this.phone = phone;
        this.address = address;
        this.delivery = delivery;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public int getDelivery() {
        return delivery;
    }
}
