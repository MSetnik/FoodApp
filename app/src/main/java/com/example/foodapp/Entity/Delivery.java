package com.example.foodapp.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "delivery_table")
public class Delivery {
    @PrimaryKey
    private int deliveryOption;
    private String yes_no;

    public Delivery(int deliveryOption, String yes_no) {
        this.deliveryOption = deliveryOption;
        this.yes_no = yes_no;
    }

    public int getDeliveryOption() {
        return deliveryOption;
    }

    public String getYes_no() {
        return yes_no;
    }
}
