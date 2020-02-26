package com.example.foodapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.Entity.DishCategory;
import com.example.foodapp.R;

import java.util.ArrayList;
import java.util.List;

public class DishCategoryAdapter extends RecyclerView.Adapter<DishCategoryAdapter.CategoryMenuViewHolder> {
    private List<DishCategory>lCategories = new ArrayList<>();
    private OnCategoryListener onCategoryListener;


    @NonNull
    @Override
    public CategoryMenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_menu,parent,false);
        CategoryMenuViewHolder holder = new CategoryMenuViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryMenuViewHolder holder, int position) {
        DishCategory dishCategory = lCategories.get(position);
        holder.CategoryName.setText(dishCategory.getCategoryName());
    }

    @Override
    public int getItemCount() {
        return lCategories.size();
    }


    public void setlCategories(List<DishCategory> Category)
    {
        this.lCategories = Category;
        notifyDataSetChanged();
    }


    public class CategoryMenuViewHolder extends RecyclerView.ViewHolder {

        TextView CategoryName;


        public CategoryMenuViewHolder(@NonNull View itemView) {
            super(itemView);
            CategoryName = itemView.findViewById(R.id.menuCategoryName);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    onCategoryListener.onCategoryClick(lCategories.get(position));

                }
            });
        }
    }

    public interface OnCategoryListener{
        void onCategoryClick(DishCategory dishCategory);
    }

    public void SetOnCategoryListener(OnCategoryListener onCategoryListener)
    {
        this.onCategoryListener = onCategoryListener;
    }

}
