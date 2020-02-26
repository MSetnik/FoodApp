package com.example.foodapp;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.foodapp.DAO.DeliveryDAO;
import com.example.foodapp.DAO.DishCategoryDAO;
import com.example.foodapp.DAO.DishDAO;
import com.example.foodapp.DAO.DishSizePriceDAO;
import com.example.foodapp.DAO.MenuDAO;
import com.example.foodapp.DAO.RestaurantDAO;
import com.example.foodapp.DAO.WishlistDAO;
import com.example.foodapp.Entity.Delivery;
import com.example.foodapp.Entity.Dish;
import com.example.foodapp.Entity.DishCategory;
import com.example.foodapp.Entity.DishSizePrice;
import com.example.foodapp.Entity.Menu;
import com.example.foodapp.Entity.Restaurant;
import com.example.foodapp.Entity.Wishlist;

import java.util.List;

public class Repository {
    private RestaurantDAO restaurantDAO;
    private DishDAO dishDAO;
    private MenuDAO menuDAO;
    private DishCategoryDAO dishCategoryDAO;
    private DeliveryDAO deliveryDAO;
    private DishSizePriceDAO dishSizePriceDAO;
    private WishlistDAO wishlistDAO;

    private LiveData<List<Restaurant>> allRestaurants;
    private LiveData<List<Menu>> allMenu;
    private LiveData<List<Dish>>allDish;
    private LiveData<List<DishCategory>>allCategory;
    private LiveData<List<Delivery>>lDelivery;
    private LiveData<List<DishSizePrice>>lDishSizePrice;



    public Repository (Application application){
        Database database = Database.getInstance(application);
        restaurantDAO = database.restaurantDAO();
        dishDAO = database.dishDAO();
        menuDAO = database.menuDAO();
        dishCategoryDAO = database.dishCategoryDAO();
        deliveryDAO = database.deliveryDAO();
        dishSizePriceDAO = database.dishSizePriceDAO();
        wishlistDAO = database.wishlistDAO();

        allRestaurants = restaurantDAO.GetAllRestaurants();
        allMenu = menuDAO.GetAllMenu();
        allDish = dishDAO.GetAllDishes();
        allCategory = dishCategoryDAO.GetAllDishCategory();
        lDelivery = deliveryDAO.GetAllDelivery();
        lDishSizePrice = dishSizePriceDAO.GetAllDishSizePrice();
    }

    public LiveData<List<Restaurant>> GetAllRestaurants(){
        return allRestaurants;
    }

    public LiveData<List<Menu>> GetAllMenu() {
        return allMenu;
    }

    public LiveData<List<Dish>> GetAllDish() {
        return allDish;
    }

    public LiveData<List<DishCategory>> GetAllCategory() {
        return allCategory;
    }

    public LiveData<List<Delivery>> GetlDelivery() {
        return lDelivery;
    }

    public LiveData<List<DishSizePrice>> GetlDishSizePrice() {
        return lDishSizePrice;
    }

    public LiveData<List<Dish>> getlDishCategory(int categoryID) {

        return dishDAO.GetCategoryDishes(categoryID);
    }


    public LiveData<List<DishCategory>> GetRestaurantCategory(int restaurantID)
    {
        return dishCategoryDAO.GetRestaurantCategory(restaurantID);
    }

    public LiveData<List<Dish>>GetRestaurantCategoryDish(int restaurantID, int categoryID)
    {
        return dishDAO.GetRestaurantCategoryDish(restaurantID, categoryID);
    }

    public LiveData<List<Restaurant>>GetRestaurantFromWishlist(int restaurantID){
        return restaurantDAO.GetRestaurantFromWishlist(restaurantID);
    }


    public void AddToWishlist(final Wishlist wishlist){

        AppExecutor.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                wishlistDAO.Insert(wishlist);
            }
        });
    }

    public LiveData<List<Wishlist>>GetRestaurantWishlist(int restaurantID)
    {
        return wishlistDAO.GetRestaurantWishlist(restaurantID);
    }


    public void DeleteWishlist()
    {
        AppExecutor.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                wishlistDAO.DeleteWishlist();
            }
        });
    }

    public void DeleteSelectedItem(final Wishlist wishlist)
    {
        AppExecutor.getInstance().getDiskIO().execute(new Runnable() {
            @Override
            public void run() {
                wishlistDAO.Delete(wishlist);
            }
        });
    }

}
