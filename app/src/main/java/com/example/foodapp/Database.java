package com.example.foodapp;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

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

@androidx.room.Database(entities = {Restaurant.class, Dish.class, DishCategory.class, Delivery.class, Menu.class}, version = 1, exportSchema = false)
public abstract class Database extends RoomDatabase {

    private static Database instance;

    public abstract RestaurantDAO restaurantDAO();

    public abstract DishDAO dishDAO();

    public abstract MenuDAO menuDAO();

    public abstract DishCategoryDAO dishCategoryDAO();

    public abstract DeliveryDAO deliveryDAO();

    //synchronized - only one thread can access this method
    public static synchronized Database getInstance(Context context){
        if(instance == null)
        {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    Database.class, "food_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsynctask(instance).execute();
        }
    };

    private static class PopulateDbAsynctask extends AsyncTask<Void, Void, Void>{
        private RestaurantDAO restaurantDAO;
        private DishDAO dishDAO;
        private MenuDAO menuDAO;
        private DishCategoryDAO dishCategoryDAO;
        private DeliveryDAO deliveryDAO;

        private PopulateDbAsynctask(Database db){
            restaurantDAO = db.restaurantDAO();
            menuDAO = db.menuDAO();
            dishDAO = db.dishDAO();
            dishCategoryDAO=db.dishCategoryDAO();
            deliveryDAO = db.deliveryDAO();

        }

        @Override
        protected Void doInBackground(Void... voids) {
            restaurantDAO.Insert(new Restaurant("Fontana" , "033/663 011", "Trg Ante Starčevića 17A, Belišće", 1));
            restaurantDAO.Insert(new Restaurant("Gurman" , "031/653-077", "Ul. Augusta Šenoe 107, 31550, Valpovo", 1));
            deliveryDAO.Insert(new Delivery(1,"Da"));
            deliveryDAO.Insert(new Delivery(0,"Ne"));
            return null;
        }
    }
}
