package com.example.foodapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.foodapp.Entity.Dish;
import com.example.foodapp.R;

import java.util.ArrayList;
import java.util.List;

public class DishAdapter extends RecyclerView.Adapter<DishAdapter.DishViewHolder> {

    private List<Dish>lDish = new ArrayList<>();
    private OnButtonDishListener onButtonDishListener;

    @NonNull
    @Override
    public DishViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.dish_layout, parent,false);
        DishViewHolder holder = new DishViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull DishViewHolder holder, int position) {
        Dish dish = lDish.get(position);
        holder.DishName.setText(dish.getDishName());
        holder.DishPrice.setText(String.format("%.2f",dish.getPrice()) + " KN");
        holder.DishDescription.setText(dish.getDishDescription());

    }


    public void setlDish(List<Dish> dish) {
        this.lDish = dish;
        notifyDataSetChanged();
    }



        @Override
    public int getItemCount() {
        return lDish.size();
    }

    class DishViewHolder extends RecyclerView.ViewHolder{
        private TextView DishName;
        private TextView DishPrice;
        private TextView DishDescription;
        private Button btnAdd;


        public DishViewHolder(@NonNull View itemView) {
            super(itemView);
            DishName = itemView.findViewById(R.id.DishName);
            DishPrice = itemView.findViewById(R.id.DishPrice);
            DishDescription = itemView.findViewById(R.id.DishDescription);
            btnAdd = itemView.findViewById(R.id.btnAdd);

            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    onButtonDishListener.onButtonClick(lDish.get(position));
                }
            });
        }
    }

    public interface OnButtonDishListener{
        void onButtonClick(Dish dish);
    }

    public void SetOnButtonListener(OnButtonDishListener onButtonDishListener)
    {
        this.onButtonDishListener = onButtonDishListener;
    }
}
