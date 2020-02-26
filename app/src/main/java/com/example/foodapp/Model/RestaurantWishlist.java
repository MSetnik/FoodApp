package com.example.foodapp.Model;

import com.example.foodapp.Entity.Wishlist;

import java.util.List;

public class RestaurantWishlist {
    private int restaurantID;
    private String RestaurantName;
    private String Phone;
    private int Delivery;
    private List<Wishlist>lRestaurantWishlist;

    public RestaurantWishlist(int restaurantID, String restaurantName, String phone, int delivery, List<Wishlist> lRestaurantWishlist) {
        this.restaurantID = restaurantID;
        RestaurantName = restaurantName;
        Phone = phone;
        Delivery = delivery;
        this.lRestaurantWishlist = lRestaurantWishlist;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public int getDelivery() {
        return Delivery;
    }

    public void setDelivery(int delivery) {
        Delivery = delivery;
    }

    public int getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(int restaurantID) {
        this.restaurantID = restaurantID;
    }

    public String getRestaurantName() {
        return RestaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        RestaurantName = restaurantName;
    }

    public List<Wishlist> getlRestaurantWishlist() {
        return lRestaurantWishlist;
    }

    public void setlRestaurantWishlist(List<Wishlist> lRestaurantWishlist) {
        this.lRestaurantWishlist = lRestaurantWishlist;
    }
}
