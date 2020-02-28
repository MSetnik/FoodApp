package com.example.foodapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.foodapp.Adapter.DishCategoryAdapter;
import com.example.foodapp.Entity.DishCategory;
import com.example.foodapp.Entity.Menu;
import com.example.foodapp.FoodAppViewModel;
import com.example.foodapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class MenuActivity extends AppCompatActivity{
    private FoodAppViewModel viewModel;
    private List<DishCategory> lCategories;
    private DishCategoryAdapter dAdapter;
    private BottomNavigationView bottomNavigationView;
    private RecyclerView recyclerView;
    private Button btnWishlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        viewModel = ViewModelProviders.of(this).get(FoodAppViewModel.class);

        RecyclerViewBind();
        CategoryListener();
        BottomNavigation();
        GetAllCategory();
        WishlistClick();

    }

    private void WishlistClick()
    {
        btnWishlist = findViewById(R.id.buttonWishlist);

        btnWishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, WishlistActivity.class);
                startActivity(intent);
            }
        });
    }

    private void CategoryListener()
    {
        dAdapter.SetOnCategoryListener(new DishCategoryAdapter.OnCategoryListener() {
            @Override
            public void onCategoryClick(DishCategory dishCategory) {
                Intent intent = new Intent(MenuActivity.this, DishActivity.class);
                intent.putExtra("category_id", dishCategory.getCategoryID());
                startActivity(intent);
            }
        });
    }

    private void RecyclerViewBind()
    {
        recyclerView = findViewById(R.id.menuRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        dAdapter = new DishCategoryAdapter();
        recyclerView.setAdapter(dAdapter);
    }

    private void BottomNavigation()
    {
        bottomNavigationView =findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.menuTab);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case R.id.restaurantsTab:
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        overridePendingTransition(0,0);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP );
                        startActivity(intent);
                        return true;

                    case R.id.menuTab:
                        return true;
                }
                return false;
            }
        });
    }

    private void GetAllCategory()
    {
        viewModel.getAllCategory().observe(this, new Observer<List<DishCategory>>() {
            @Override
            public void onChanged(List<DishCategory> dishCategories) {
                lCategories = dishCategories;
                dAdapter.setlCategories(lCategories);
            }
        });
    }


}
