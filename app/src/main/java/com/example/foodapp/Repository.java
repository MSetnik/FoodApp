package com.example.foodapp;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.foodapp.DAO.DeliveryDAO;
import com.example.foodapp.DAO.DishCategoryDAO;
import com.example.foodapp.DAO.DishDAO;
import com.example.foodapp.DAO.MenuDAO;
import com.example.foodapp.DAO.RestaurantDAO;
import com.example.foodapp.Entity.Delivery;
import com.example.foodapp.Entity.Dish;
import com.example.foodapp.Entity.DishCategory;
import com.example.foodapp.Entity.Menu;
import com.example.foodapp.Entity.Restaurant;

import java.util.List;

public class Repository {
    private RestaurantDAO restaurantDAO;
    private DishDAO dishDAO;
    private MenuDAO menuDAO;
    private DishCategoryDAO dishCategoryDAO;
    private DeliveryDAO deliveryDAO;

    private LiveData<List<Restaurant>> allRestaurants;
    private List<Menu> allMenu;
    private List<Dish>allDish;
    private List<DishCategory>allCategory;
    private List<Delivery>lDelivery;

    public Repository (Application application){
        Database database = Database.getInstance(application);
        restaurantDAO = database.restaurantDAO();
        /*dishDAO = database.dishDAO();
        menuDAO = database.menuDAO();
        dishCategoryDAO = database.dishCategoryDAO();
        deliveryDAO = database.deliveryDAO();*/

        allRestaurants = restaurantDAO.GetAllRestaurants();
/*        allMenu = menuDAO.GetAllMenu();
        allDish = dishDAO.GetAllDishes();
        allCategory = dishCategoryDAO.GetAllDishCategory();
        lDelivery = deliveryDAO.GetAllDelivery();*/
    }

    public LiveData<List<Restaurant>> GetAllRestaurants(){
        return allRestaurants;
    }

    public void insert (Restaurant restaurant){
        new InsertRestaurantAsyncTask(restaurantDAO).execute(restaurant);
    }

    public static class InsertRestaurantAsyncTask extends AsyncTask<Restaurant, Void, Void>{
        private RestaurantDAO restaurantDAO;

        public InsertRestaurantAsyncTask(RestaurantDAO restaurantDAO) {
            this.restaurantDAO = restaurantDAO;
        }

        @Override
        protected Void doInBackground(Restaurant... restaurants) {
            restaurantDAO.Insert(restaurants[0]);
            return null;
        }
    }

  /*  public List<Menu> GetMenu(){
        return allMenu;
    }

    public List<Delivery> GetDelivery(){
        return lDelivery;
    }
    public List<Dish> GetDish(){
        return allDish;
    }
    public List<DishCategory> GetCategory(){
        return allCategory;
    }*/

}
