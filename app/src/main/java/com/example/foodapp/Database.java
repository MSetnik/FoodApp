package com.example.foodapp;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
/*
import com.example.foodapp.DAO.DeliveryDAO;
import com.example.foodapp.DAO.DishCategoryDAO;
import com.example.foodapp.DAO.DishDAO;
import com.example.foodapp.DAO.MenuDAO;*/
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
import com.example.foodapp.Entity.Delivery;
import com.example.foodapp.Entity.Restaurant;
import com.example.foodapp.Entity.Wishlist;

@androidx.room.Database(entities = {Restaurant.class, Dish.class, DishCategory.class, Delivery.class, Menu.class, DishSizePrice.class, Wishlist.class}, version = 7, exportSchema = false)
public abstract class Database extends RoomDatabase {

    private static Database instance;

    public abstract RestaurantDAO restaurantDAO();

    public abstract DishDAO dishDAO();

    public abstract MenuDAO menuDAO();

    public abstract DishCategoryDAO dishCategoryDAO();

    public abstract DeliveryDAO deliveryDAO();

    public abstract DishSizePriceDAO dishSizePriceDAO();

    public abstract WishlistDAO wishlistDAO();

    //synchronized - only one thread can access this method
    public static synchronized Database getInstance(final Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    Database.class, "Food_databassse")
                    .fallbackToDestructiveMigration()
                    .addCallback(new Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            AppExecutor.getInstance().getDiskIO().execute(new Runnable() {
                                @Override
                                public void run() {
                                    //upis restorana
                                    getInstance(context).restaurantDAO().Insert(new Restaurant("Fontana", "033/663 011", "Trg Ante Starčevića 17A, Belišće", 1));
                                    getInstance(context).restaurantDAO().Insert(new Restaurant("Gurman", "031/653-077", "Ul. Augusta Šenoe 107, 31550, Valpovo", 1));
                                    //upis dostave
                                    getInstance(context).deliveryDAO().Insert(new Delivery(1, "Da"));
                                    getInstance(context).deliveryDAO().Insert(new Delivery(2, "Ne"));
                                    //upis kategorija
                                    getInstance(context).dishCategoryDAO().Insert(new DishCategory("Juhe")); //1
                                    getInstance(context).dishCategoryDAO().Insert(new DishCategory("Topla jela i doručak")); //2
                                    getInstance(context).dishCategoryDAO().Insert(new DishCategory("Hladna predjela")); //3
                                    getInstance(context).dishCategoryDAO().Insert(new DishCategory("Jela od svinjetine")); //4
                                    getInstance(context).dishCategoryDAO().Insert(new DishCategory("Jela od puretine i piletine")); //5
                                    getInstance(context).dishCategoryDAO().Insert(new DishCategory("Jela s roštilja")); //6
                                    getInstance(context).dishCategoryDAO().Insert(new DishCategory("Lignje")); //7
                                    getInstance(context).dishCategoryDAO().Insert(new DishCategory("Rižoto")); //8
                                    getInstance(context).dishCategoryDAO().Insert(new DishCategory("Salate")); //9
                                    getInstance(context).dishCategoryDAO().Insert(new DishCategory("Sendviči")); //10
                                    getInstance(context).dishCategoryDAO().Insert(new DishCategory("Slastice")); //11
                                    getInstance(context).dishCategoryDAO().Insert(new DishCategory("Pizze")); //12
                                    getInstance(context).dishCategoryDAO().Insert(new DishCategory("Tjestenine")); //13
                                    getInstance(context).dishCategoryDAO().Insert(new DishCategory("Umaci")); //14
                                    getInstance(context).dishCategoryDAO().Insert(new DishCategory("Prilozi")); //15
                                    //upis jela - juhe
                                    getInstance(context).dishDAO().Insert(new Dish("Juha goveđa", "", 18.00, 1, 1));  //1
                                    getInstance(context).dishDAO().Insert(new Dish("Juha od Rajčice", "", 18.00, 1, 1));  //2
                                    getInstance(context).dishDAO().Insert(new Dish("Krem juha od gljiva", "", 18.00, 1, 1)); //3
                                    // topla jela i doručak
                                    getInstance(context).dishDAO().Insert(new Dish("Pohani sir", "", 33.00, 2, 1)); //4
                                    getInstance(context).dishDAO().Insert(new Dish("Pohane gljive", "", 33.00, 2, 1)); //5
                                    getInstance(context).dishDAO().Insert(new Dish("Hemendex", "", 25.00, 2, 1)); //6
                                    getInstance(context).dishDAO().Insert(new Dish("Bacondex", "", 30.00, 2, 1)); //7
                                    getInstance(context).dishDAO().Insert(new Dish("Omlet", "", 25.00, 2, 1)); //8
                                    getInstance(context).dishDAO().Insert(new Dish("Doručak Fontana", "", 30.00, 2, 1)); //9
                                    //hladna predjela
                                    getInstance(context).dishDAO().Insert(new Dish("Kulen", "100g", 30.00, 3, 1)); //10
                                    getInstance(context).dishDAO().Insert(new Dish("Pršut", "100g", 30.00, 3, 1)); //11
                                    getInstance(context).dishDAO().Insert(new Dish("Suhi vrat", "100g", 15.00, 3, 1)); //12
                                    getInstance(context).dishDAO().Insert(new Dish("Sir gauda", "100g", 15.00, 3, 1)); //13
                                    //jela od svinjetine
                                    getInstance(context).dishDAO().Insert(new Dish("Bečki odrezak", "svinjski odrezak, pommes, pecivo iz krušne peći", 40.00, 4, 1)); //14
                                    getInstance(context).dishDAO().Insert(new Dish("Pariški odrezak", "svinjski odrezak, pommes, pecivo iz krušne peći", 40.00, 4, 1)); //15
                                    getInstance(context).dishDAO().Insert(new Dish("Naravni odrezak", "svinjski odrezak, pommes, pecivo iz krušne peći", 40.00, 4, 1)); //16
                                    getInstance(context).dishDAO().Insert(new Dish("Zagrebački odrezak", "svinjski odrezak, pomes, kruščići iz krušne peći", 47.00, 4, 1)); //17
                                    getInstance(context).dishDAO().Insert(new Dish("Zrinski odrezak", "svinjski odrezak, šampinjoni, sir, šunka, pommes, pecivo iz krušne peći", 47.00, 4, 1)); //18
                                    getInstance(context).dishDAO().Insert(new Dish("Vješalica", "svinjski odrezak, svježi sir, kiseli krastavci, šunka, pommes, kruščići", 50.00, 4, 1)); //19
                                    getInstance(context).dishDAO().Insert(new Dish("Odrezak u umaku od gljiva", "svinjski odrezak, šampinjoni, senf, pommes, kruščići", 50.00, 4, 1)); //20
                                    //Jela od puretine i piletine
                                    getInstance(context).dishDAO().Insert(new Dish("Piletina sa žara", "piletina, pommes, pecivo iz krušne peći", 40.00, 5, 1));  //21
                                    getInstance(context).dishDAO().Insert(new Dish("Punjena pileća prsa", "pileći file, sir gouda, pršut, pommes, kruščići", 47.00, 5, 1)); //22
                                    getInstance(context).dishDAO().Insert(new Dish("Punjena pileća vješalica", "pileći file, sir svježi, šunka, pommes, kruščići, kiseli krastavci", 50.00, 5, 1)); //23
                                    getInstance(context).dishDAO().Insert(new Dish("Ražnjići pileći", "pileći file, pommes, kruščići", 38.00, 5, 1)); //24
                                    getInstance(context).dishDAO().Insert(new Dish("Pileći rižoto", "pileći file, riža, povrće", 34.00, 5, 1)); //25
                                    getInstance(context).dishDAO().Insert(new Dish("Pileći bečki odrezak", "pureći file, pommes, kruščići", 40.00, 5, 1));  //26
                                    getInstance(context).dishDAO().Insert(new Dish("Pureći bečki odrezak", "pureći file, pommes, kruščići", 60.00, 5, 1)); //27
                                    getInstance(context).dishDAO().Insert(new Dish("Ražnjići pureći sa pancetom i povrćem", "pureći file, pommes, kruščići", 60.00, 5, 1)); //28
                                    getInstance(context).dishDAO().Insert(new Dish("Puretina u umaku od vrhnja", "pureći file, vrhnje za kuhanje, pommes, kruščići", 60.00, 5, 1)); //29
                                    // Jela s roštilja
                                    getInstance(context).dishDAO().Insert(new Dish("Čevapčići", "10 čevapčića, lepinja, luk", 33.00, 6, 1)); //30
                                    getInstance(context).dishDAO().Insert(new Dish("Čevapčići mali", "5 čevapčića, lepinja, luk", 23.00, 6, 1)); //31
                                    getInstance(context).dishDAO().Insert(new Dish("Ražnjići", "2 ražnjića, lepinja, luk", 35.00, 6, 1)); //32
                                    getInstance(context).dishDAO().Insert(new Dish("Mješano meso", "čevapčići, pljeskavica, ražnijći, kotlet, kobasica, slanina, lepinja, pommes", 55.00, 6, 1)); //33
                                    getInstance(context).dishDAO().Insert(new Dish("Pljeskavica", "pljeskavica, lepinja, luk", 33.00, 6, 1)); //34
                                    getInstance(context).dishDAO().Insert(new Dish("Pljeskavica mala", "pljeskavica, lepinja, luk", 23.00, 6, 1)); //35
                                    getInstance(context).dishDAO().Insert(new Dish("Hamburger", "hamburger, pecivo, salata - kečap/majoneza/senf", 18.00, 6, 1)); //36
                                    getInstance(context).dishDAO().Insert(new Dish("Hamburger urnebes", "hamburger, pecivo, salata, ajvar, kečap, majoneza, senf", 22.00, 6, 1)); //37
                                    getInstance(context).dishDAO().Insert(new Dish("Cheeseburger", "hamburger, pecivo, salata, sir - kečap/majoneza/senf", 20.00, 6, 1)); //38
                                    getInstance(context).dishDAO().Insert(new Dish("NLO pljeskavica", "", 45.00, 6, 1)); //39
                                    getInstance(context).dishDAO().Insert(new Dish("Gljive sa žara", "gljive, pommes, pecivo iz krušne peći", 33.00, 6, 1)); //40
                                    getInstance(context).dishDAO().Insert(new Dish("Roštilj kobasica", "kobasica, lepinja, luk", 35.00, 6, 1)); //41
                                    getInstance(context).dishDAO().Insert(new Dish("Roštilj vrat", "svinjski vrat, pommes, lepinja, luk", 40.00, 6, 1)); //42
                                    getInstance(context).dishDAO().Insert(new Dish("Pljeskavica punjena", "lepinja, pljeskavica, sir, pommes, luk", 40.00, 6, 1));  //43
                                    getInstance(context).dishDAO().Insert(new Dish("Pljeskavica fontana", "lepinja, pljeskavica, sir, pommes, luk", 45.00, 6, 1)); //44
                                    //Lignje
                                    getInstance(context).dishDAO().Insert(new Dish("Lignje sa žara", "lignje, krumpir, tršćanski umak, limun, pecivo iz krušne peći", 55.00, 7, 1)); //45
                                    getInstance(context).dishDAO().Insert(new Dish("Lignje Frigane", "lignje, pommes, tartar umak, limun, pecivo iz krušne peći", 55.00, 7, 1)); //46
                                    getInstance(context).dishDAO().Insert(new Dish("Lignje punjene", "lignje, sir, pršut, krumpir, tršćanski umak, limun, pecivo iz krušne peći", 60.00, 7, 1)); //47
                                    //Rižoto
                                    getInstance(context).dishDAO().Insert(new Dish("Risotto al fungi", "bijeli umak,gljive, bijelo vino", 33.00, 8, 1)); //48
                                    getInstance(context).dishDAO().Insert(new Dish("Risotto di parma", "sir parmezan, bijelo vino", 33.00, 8, 1)); //49
                                    getInstance(context).dishDAO().Insert(new Dish("Risotto od dagnji, liganja ili kozica", "češnjak, bijeli umak, bijelo vino", 40.00, 8, 1)); //50
                                    //Salate
                                    getInstance(context).dishDAO().Insert(new Dish("Salata tuna", "zelena salata, rajčica, luk, kukuruz, sir, tunjevina, bosiljak, kuhano jaje", 32.00, 9, 1)); //51
                                    getInstance(context).dishDAO().Insert(new Dish("Salata fontana", "zelena salata, kupus, rajčica, paprika, kukuruz, sir, šunka, peršinov list", 30.00, 9, 1)); //52
                                    getInstance(context).dishDAO().Insert(new Dish("Salata šopska", "svježa paprika, rajčica, krastavci, crveni luk, svježi sir, ljuti feferoni", 25.00, 9, 1)); //53
                                    getInstance(context).dishDAO().Insert(new Dish("Salata s piletinom", "piletina, razno povrće, tijesto fusili, dresing", 30.00, 9, 1)); //54
                                    getInstance(context).dishDAO().Insert(new Dish("Salata sezonska", "zelena, kupus, rajčica, paprika, kis. krastavci, svj. krastavci", 12.00, 9, 1)); //55
                                    // Sendviči
                                    getInstance(context).dishDAO().Insert(new Dish("Sendvič mali", "sir, šunka, krastavci", 15.00, 10, 1)); //56
                                    getInstance(context).dishDAO().Insert(new Dish("Sendvič kulen", "kulen, sir, krastavci", 25.00, 10, 1)); //57
                                    getInstance(context).dishDAO().Insert(new Dish("Sendvič pršut", "pršut, sir, krastavci", 25.00, 10, 1)); //58
                                    getInstance(context).dishDAO().Insert(new Dish("Sendvič tuna", "tunjevina - komadi, kis. krastavci, luk, sir", 25.00, 10, 1)); //59
                                    getInstance(context).dishDAO().Insert(new Dish("Sendvič veliki", "sir, šunka, krastavci", 20.00, 10, 1)); //60
                                    getInstance(context).dishDAO().Insert(new Dish("Sendvič vegetarijana", "svj. paprika, svj. rajčica, miješano povrće, sir", 25.00, 10, 1)); //61
                                    getInstance(context).dishDAO().Insert(new Dish("Sendvič fontana", "suhi vrat, kulen, pršut, sir, zelena salata", 25.00, 10, 1)); //62
                                    getInstance(context).dishDAO().Insert(new Dish("Sendvič od pohane piletine", "pohana piletina, sir, tartar, zelena salata", 18.00, 10, 1)); //63
                                    //Slastice
                                    getInstance(context).dishDAO().Insert(new Dish("Palačinke marmelada", "3 kom", 18.00, 11, 1)); //64
                                    getInstance(context).dishDAO().Insert(new Dish("Palačinke čokolada", "3 kom", 18.00, 11, 1)); //65
                                    getInstance(context).dishDAO().Insert(new Dish("Palačinke zapečene sa sirom", "3 kom", 25.00, 11, 1)); //66
                                    //Pizze
                                    getInstance(context).dishDAO().Insert(new Dish("Margarita", "pelat, sir", 27.00, 12, 1)); //67
                                    getInstance(context).dishSizePriceDAO().Insert(new DishSizePrice(67, "Mala", 25.00));
                                    getInstance(context).dishSizePriceDAO().Insert(new DishSizePrice(67, "Jumbo", 60.00));
                                    getInstance(context).dishSizePriceDAO().Insert(new DishSizePrice(67, "Gigant", 100.00));
                                    getInstance(context).dishDAO().Insert(new Dish("Fungi", "pelat, sir, gljive", 29.00, 12, 1)); //68
                                    getInstance(context).dishSizePriceDAO().Insert(new DishSizePrice(68, "Mala", 25.00));
                                    getInstance(context).dishSizePriceDAO().Insert(new DishSizePrice(68, "Jumbo", 60.00));
                                    getInstance(context).dishSizePriceDAO().Insert(new DishSizePrice(68, "Gigant", 105.00));
                                    getInstance(context).dishDAO().Insert(new Dish("Capriciosa", "pelat, šunka, gljive sir", 33.00, 12, 1)); //69
                                    getInstance(context).dishSizePriceDAO().Insert(new DishSizePrice(69, "Mala", 25.00));
                                    getInstance(context).dishSizePriceDAO().Insert(new DishSizePrice(69, "Jumbo", 65.00));
                                    getInstance(context).dishSizePriceDAO().Insert(new DishSizePrice(69, "Gigant", 110.00));
                                    getInstance(context).dishDAO().Insert(new Dish("Calzone (preklopljena)", "pelat, šunka, gljive, sir", 35.00, 12, 1)); //70
                                    getInstance(context).dishSizePriceDAO().Insert(new DishSizePrice(70, "Mala", 25.00));
                                    getInstance(context).dishDAO().Insert(new Dish("Slavonska", "pelat, šunka, gljive, sir, slanina, kulen, ljuti feferoni, luk (mala)", 35.00, 12, 1)); //71
                                    getInstance(context).dishSizePriceDAO().Insert(new DishSizePrice(71, "Mala", 26.00));
                                    getInstance(context).dishSizePriceDAO().Insert(new DishSizePrice(71, "Jumbo", 70.00));
                                    getInstance(context).dishSizePriceDAO().Insert(new DishSizePrice(71, "Gigant", 120.00));
                                    getInstance(context).dishDAO().Insert(new Dish("Fontana", "pelat, šunka, gljive, sir, slanina, kulen, suhi vrat, ljuti feferoni, jaje, luk", 38.00, 12, 1)); //72
                                    getInstance(context).dishSizePriceDAO().Insert(new DishSizePrice(72, "Mala", 27.00));
                                    getInstance(context).dishSizePriceDAO().Insert(new DishSizePrice(72, "Jumbo", 75.00));
                                    getInstance(context).dishSizePriceDAO().Insert(new DishSizePrice(72, "Gigant", 125.00));
                                    getInstance(context).dishDAO().Insert(new Dish("Vegetariana", "pelat, gljive, sir, rajčica, paprika, kukuruz, artičoke, masline", 38.00, 12, 1)); //73
                                    getInstance(context).dishSizePriceDAO().Insert(new DishSizePrice(73, "Mala", 27.00));
                                    getInstance(context).dishSizePriceDAO().Insert(new DishSizePrice(73, "Jumbo", 75.00));
                                    getInstance(context).dishSizePriceDAO().Insert(new DishSizePrice(73, "Gigant", 120.00));
                                    getInstance(context).dishDAO().Insert(new Dish("Quatro stagioni", "pelat, šunka, gljive, sir, morski plodovi", 39.00, 12, 1)); //74
                                    getInstance(context).dishSizePriceDAO().Insert(new DishSizePrice(74, "Mala", 27.00));
                                    getInstance(context).dishSizePriceDAO().Insert(new DishSizePrice(74, "Jumbo", 75.00));
                                    getInstance(context).dishSizePriceDAO().Insert(new DishSizePrice(74, "Gigant", 120.00));
                                    getInstance(context).dishDAO().Insert(new Dish("Formaggi", "pelat, sir gauda, sir edamer, sir gorgonzola, vrhnje", 40.00, 12, 1)); //75
                                    getInstance(context).dishSizePriceDAO().Insert(new DishSizePrice(75, "Mala", 27.00));
                                    getInstance(context).dishSizePriceDAO().Insert(new DishSizePrice(75, "Jumbo", 75.00));
                                    getInstance(context).dishSizePriceDAO().Insert(new DishSizePrice(75, "Gigant", 120.00));
                                    getInstance(context).dishDAO().Insert(new Dish("Rustica", "pelat, sir, mljeveno meso, luk, gljive, slanina", 40.00, 12, 1)); //76
                                    getInstance(context).dishSizePriceDAO().Insert(new DishSizePrice(76, "Mala", 28.00));
                                    getInstance(context).dishSizePriceDAO().Insert(new DishSizePrice(76, "Jumbo", 75.00));
                                    getInstance(context).dishSizePriceDAO().Insert(new DishSizePrice(76, "Gigant", 120.00));
                                    getInstance(context).dishDAO().Insert(new Dish("Mexicana", "pelat, sir, mljeveno meso, luk, kukuruz, ljuti feferoni", 40.00, 12, 1)); //77
                                    getInstance(context).dishSizePriceDAO().Insert(new DishSizePrice(77, "Mala", 27.00));
                                    getInstance(context).dishSizePriceDAO().Insert(new DishSizePrice(77, "Jumbo", 75.00));
                                    getInstance(context).dishSizePriceDAO().Insert(new DishSizePrice(77, "Gigant", 125.00));
                                    getInstance(context).dishDAO().Insert(new Dish("Szalami", "pelat, bečka salama, sir", 38.00, 12, 1)); //78
                                    getInstance(context).dishSizePriceDAO().Insert(new DishSizePrice(78, "Mala", 28.00));
                                    getInstance(context).dishSizePriceDAO().Insert(new DishSizePrice(78, "Jumbo", 75.00));
                                    getInstance(context).dishSizePriceDAO().Insert(new DishSizePrice(78, "Gigant", 120.00));
                                    getInstance(context).dishDAO().Insert(new Dish("113", "pelat, kulen, sir, luk, paprika, ljuti feferoni, bijeli luk", 38.00, 12, 1)); //79
                                    getInstance(context).dishSizePriceDAO().Insert(new DishSizePrice(79, "Mala", 28.00));
                                    getInstance(context).dishSizePriceDAO().Insert(new DishSizePrice(79, "Jumbo", 75.00));
                                    getInstance(context).dishSizePriceDAO().Insert(new DishSizePrice(79, "Gigant", 125.00));
                                    getInstance(context).dishDAO().Insert(new Dish("Bianca", "vrhnje, šunka, sir", 35.00, 12, 1)); //80
                                    getInstance(context).dishSizePriceDAO().Insert(new DishSizePrice(80, "Mala", 26.00));
                                    getInstance(context).dishSizePriceDAO().Insert(new DishSizePrice(80, "Jumbo", 65.00));
                                    getInstance(context).dishSizePriceDAO().Insert(new DishSizePrice(80, "Gigant", 110.00));
                                    getInstance(context).dishDAO().Insert(new Dish("Tono", "pelat, sir, tuna, luk", 40.00, 12, 1)); //81
                                    getInstance(context).dishSizePriceDAO().Insert(new DishSizePrice(81, "Mala", 28.00));
                                    getInstance(context).dishSizePriceDAO().Insert(new DishSizePrice(81, "Jumbo", 70.00));
                                    getInstance(context).dishSizePriceDAO().Insert(new DishSizePrice(81, "Gigant", 120.00));
                                    getInstance(context).dishDAO().Insert(new Dish("Mafia", "pelat sir, tuna, škampi, dagnje, kapari, artičoke", 40.00, 12, 1)); //82
                                    getInstance(context).dishSizePriceDAO().Insert(new DishSizePrice(82, "Mala", 30.00));
                                    getInstance(context).dishSizePriceDAO().Insert(new DishSizePrice(82, "Jumbo", 70.00));
                                    getInstance(context).dishSizePriceDAO().Insert(new DishSizePrice(82, "Gigant", 125.00));
                                    getInstance(context).dishDAO().Insert(new Dish("Havaii", "pelat, šunka, sir, ananas", 38.00, 12, 1)); //83
                                    getInstance(context).dishSizePriceDAO().Insert(new DishSizePrice(83, "Mala", 28.00));
                                    getInstance(context).dishSizePriceDAO().Insert(new DishSizePrice(83, "Jumbo", 70.00));
                                    getInstance(context).dishSizePriceDAO().Insert(new DishSizePrice(83, "Gigant", 125.00));
                                    getInstance(context).dishDAO().Insert(new Dish("Šunka", "pelat, šunka, sir", 32.00, 12, 1)); //84
                                    getInstance(context).dishSizePriceDAO().Insert(new DishSizePrice(84, "Mala", 24.00));
                                    getInstance(context).dishSizePriceDAO().Insert(new DishSizePrice(84, "Jumbo", 60.00));
                                    getInstance(context).dishSizePriceDAO().Insert(new DishSizePrice(84, "Gigant", 110.00));
                                    getInstance(context).dishDAO().Insert(new Dish("Dalmatina", "pelat, sir gouda, pršut, ovčji sir (masline)", 40.00, 12, 1)); //85
                                    getInstance(context).dishSizePriceDAO().Insert(new DishSizePrice(85, "Mala", 28.00));
                                    getInstance(context).dishSizePriceDAO().Insert(new DishSizePrice(85, "Jumbo", 75.00));
                                    getInstance(context).dishSizePriceDAO().Insert(new DishSizePrice(85, "Gigant", 125.00));

                                    //Tjestenine
                                    getInstance(context).dishDAO().Insert(new Dish("Spaghetti", "", 11.00, 13, 1));
                                    getInstance(context).dishDAO().Insert(new Dish("Tagiatelle verde - zeleni rezanci", "", 11.00, 13, 1));
                                    getInstance(context).dishDAO().Insert(new Dish("Penne - maccharoni", "", 11.00, 13, 1));
                                    getInstance(context).dishDAO().Insert(new Dish("Fusilli - spirale", "", 11.00, 13, 1));
                                    getInstance(context).dishDAO().Insert(new Dish("Integralna tjestenina", "", 15.00, 13, 1));
                                    getInstance(context).dishDAO().Insert(new Dish("Gnocchi - njoki", "", 11.00, 13, 1));
                                    getInstance(context).dishDAO().Insert(new Dish("Penne tricolori - tijesto 3 boje", "", 11.00, 13, 1));
                                    // umaci
                                    getInstance(context).dishDAO().Insert(new Dish("Prosciutto umak", "bijeli umak, pršut, gljive, bosiljak", 19.00, 14, 1));
                                    getInstance(context).dishDAO().Insert(new Dish("Umak alla gorgonzola", "bešamel, gorgonzola, bijeli umak, začini", 19.00, 14, 1));
                                    getInstance(context).dishDAO().Insert(new Dish("Umak fungi", "šampinjoni,slatko vrhnje, bijelo vino", 17.00, 14, 1));
                                    getInstance(context).dishDAO().Insert(new Dish("Umak morski plodovi", "bijeli umak, morski plodovi, bijelo vino, češnjak", 20.00, 14, 1));
                                    getInstance(context).dishDAO().Insert(new Dish("Umak carbonara", "bijeli umak, šunka, slanina, začini", 9.00, 14, 1));
                                    getInstance(context).dishDAO().Insert(new Dish("Umak bologneze", "mljeveno meso, pelat, začini", 19.00, 14, 1));
                                    getInstance(context).dishDAO().Insert(new Dish("Umak milanese", "šunka, špek, pelat, češnjak", 19.00, 14, 1));
                                    getInstance(context).dishDAO().Insert(new Dish("Umak mama bianca", "piletina, bijeli umak, bosiljak", 19.00, 14, 1));
                                    //prilozi
                                    getInstance(context).dishDAO().Insert(new Dish("Pommes fritess", "", 10.00, 15, 1));
                                    getInstance(context).dishDAO().Insert(new Dish("Povrće miješano", "", 11.00, 15, 1));
                                    getInstance(context).dishDAO().Insert(new Dish("Ajvar", "", 2.00, 15, 1));
                                    getInstance(context).dishDAO().Insert(new Dish("Kečap", "", 2.00, 15, 1));
                                    getInstance(context).dishDAO().Insert(new Dish("Majoneza", "", 3.00, 15, 1));
                                    getInstance(context).dishDAO().Insert(new Dish("Senf", "", 2.00, 15, 1));
                                    getInstance(context).dishDAO().Insert(new Dish("Vrhnje kiselo", "", 5.00, 15, 1));
                                    getInstance(context).dishDAO().Insert(new Dish("Masline", "", 5.00, 15, 1));
                                    getInstance(context).dishDAO().Insert(new Dish("Jaja", "", 5.00, 15, 1));
                                    getInstance(context).dishDAO().Insert(new Dish("Feferoni", "", 3.00, 15, 1));
                                    getInstance(context).dishDAO().Insert(new Dish("Lepinja zapečena", "", 8.00, 15, 1));
                                    getInstance(context).dishDAO().Insert(new Dish("Sir za pizzu", "", 7.00, 15, 1));
                                    getInstance(context).dishDAO().Insert(new Dish("Gljive", "", 5.00, 15, 1));
                                    getInstance(context).dishDAO().Insert(new Dish("Kulen", "", 7.00, 15, 1));
                                    getInstance(context).dishDAO().Insert(new Dish("Suhi vrat", "", 7.00, 15, 1));
                                    getInstance(context).dishDAO().Insert(new Dish("Slanina", "", 10.00, 15, 1));
                                    getInstance(context).dishDAO().Insert(new Dish("Kukuruz", "", 5.00, 15, 1));
                                    getInstance(context).dishDAO().Insert(new Dish("Krastavci kiseli", "", 3.00, 15, 1));
                                    getInstance(context).dishDAO().Insert(new Dish("Šunka za pizzu", "", 5.00, 15, 1));
                                    getInstance(context).dishDAO().Insert(new Dish("Kruščići iz krušne peći", "", 8.00, 15, 1));
                                    getInstance(context).dishDAO().Insert(new Dish("Kroketi", "", 11.00, 15, 1));
                                    getInstance(context).dishDAO().Insert(new Dish("Njoke", "", 10.00, 15, 1));


                                    //meni
                                    getInstance(context).menuDAO().Insert(new Menu(1, 1));
                                    getInstance(context).menuDAO().Insert(new Menu(1, 2));
                                    getInstance(context).menuDAO().Insert(new Menu(1, 3));
                                    getInstance(context).menuDAO().Insert(new Menu(1, 4));
                                    getInstance(context).menuDAO().Insert(new Menu(1, 5));
                                    getInstance(context).menuDAO().Insert(new Menu(1, 6));
                                    getInstance(context).menuDAO().Insert(new Menu(1, 7));
                                    getInstance(context).menuDAO().Insert(new Menu(1, 8));
                                    getInstance(context).menuDAO().Insert(new Menu(1, 9));
                                    getInstance(context).menuDAO().Insert(new Menu(1, 10));
                                    getInstance(context).menuDAO().Insert(new Menu(1, 11));
                                    getInstance(context).menuDAO().Insert(new Menu(1, 12));
                                    getInstance(context).menuDAO().Insert(new Menu(1, 13));
                                    getInstance(context).menuDAO().Insert(new Menu(1, 14));
                                    getInstance(context).menuDAO().Insert(new Menu(1, 15));
                                    getInstance(context).menuDAO().Insert(new Menu(2, 15));

                                    //Rižoto
                                    getInstance(context).dishDAO().Insert(new Dish("Pommes fritess Gurman", "", 10.00, 15, 2));
                                    getInstance(context).dishDAO().Insert(new Dish("Povrće miješano Gurman", "", 11.00, 15, 2));
                                    getInstance(context).dishDAO().Insert(new Dish("Ajvar Gurman", "", 2.00, 15, 2));
                                    getInstance(context).dishDAO().Insert(new Dish("Kečap Gurman", "", 2.00, 15, 2));

                                }
                            });
                        }
                    })
                    .build();
        }
        return instance;
    }
}
