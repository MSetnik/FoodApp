package com.example.foodapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.foodapp.Entity.Delivery;
import com.example.foodapp.Entity.Dish;
import com.example.foodapp.Entity.DishCategory;
import com.example.foodapp.Entity.DishSizePrice;
import com.example.foodapp.Entity.Menu;
import com.example.foodapp.Entity.Restaurant;
import com.example.foodapp.Entity.Wishlist;

import java.util.ArrayList;
import java.util.List;

public class FoodAppViewModel extends AndroidViewModel {
    private Repository repository;
    private LiveData<List<Restaurant>> allRestaurants;
    private LiveData<List<Dish>> allDish;
    private LiveData<List<Menu>> allMenu;
    private LiveData<List<DishCategory>>allCategory;
    private LiveData<List<Delivery>>lDelivery;
    private LiveData<List<DishSizePrice>>lDishSizePrice;
    private static String TAG = "foodapp";

    private List<Wishlist>lRestaurantWishlist = new ArrayList<>();
    private OnWishlistFinishListenerVM onWishlistFinishListenerVM;


    //Context is Application instead context because view model can outlive activity (memory leak)
    public FoodAppViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
        allRestaurants = repository.GetAllRestaurants();
        allDish = repository.GetAllDish();
        allMenu = repository.GetAllMenu();
        allCategory = repository.GetAllCategory();
        lDelivery = repository.GetlDelivery();
        lDishSizePrice = repository.GetlDishSizePrice();

        repository.SetOnFinishListener(new Repository.OnWishlistFinishListener() {
            @Override
            public void OnFinish(List<Wishlist> wishlist) {
                onWishlistFinishListenerVM.OnFinish(wishlist);
            }
        });

    }

    public LiveData<List<Restaurant>> getAllRestaurants(){
        return allRestaurants;
    }


    public LiveData<List<DishCategory>> getAllCategory() {
        return allCategory;
    }

    public LiveData<List<Delivery>> getlDelivery() {
        return lDelivery;
    }

    public LiveData<List<DishSizePrice>> getlDishSizePrice() {
        return lDishSizePrice;
    }

    public LiveData<List<Dish>> getlDishCategory(int categoryID) {
        return repository.getlDishCategory(categoryID);
    }


    public LiveData<List<DishCategory>> GetRestaurantCategory(int restaurantID)
    {
        return repository.GetRestaurantCategory(restaurantID);

    }

    public LiveData<List<Dish>> GetRestaurantCategoryDish(int restaurantID, int categoryID)
    {
        return repository.GetRestaurantCategoryDish(restaurantID, categoryID);

    }

    public LiveData<List<Restaurant>>GetRestaurantFromWishlist(int restaurantID)
    {
        return repository.GetRestaurantFromWishlist(restaurantID);
    }

    public void AddToWishlist(Wishlist wishlist)
    {
        repository.AddToWishlist(wishlist);
    }

    public void GetRestaurantWishlist(final int restaurantID)
    {
         repository.GetRestaurantWishlist(restaurantID);
    }


    public void DeleteWishlist()
    {
        repository.DeleteWishlist();
    }

    public void DeleteSelectedItem(Wishlist wishlist)
    {
        repository.DeleteSelectedItem(wishlist);
    }

    public LiveData<List<Wishlist>> GetWishlist()
    {
        return repository.GetWishlist();
    }



    public interface OnWishlistFinishListenerVM
    {
        void OnFinish(List<Wishlist> wishlist);
    }

    public void SetOnFinishListener(OnWishlistFinishListenerVM listenerVM)
    {
        this.onWishlistFinishListenerVM = listenerVM;
    }
}
