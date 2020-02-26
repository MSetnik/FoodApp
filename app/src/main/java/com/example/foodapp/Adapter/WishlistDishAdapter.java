package com.example.foodapp.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.foodapp.Entity.Wishlist;
import com.example.foodapp.R;

import java.util.List;

public class WishlistDishAdapter extends RecyclerView.Adapter<WishlistDishAdapter.WishlistDishVH> {

    private List<Wishlist>lWishlist;
    private OnButtonClickListener onButtonClickRemove;

    @NonNull
    @Override
    public WishlistDishVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.wishlist_dish_layout, parent, false);
        WishlistDishVH holder = new WishlistDishVH(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull WishlistDishVH holder, int position) {
        Wishlist restaurantWishlist = lWishlist.get(position);

        holder.DishName.setText(restaurantWishlist.getDishName());
        holder.DishPrice.setText(String.format("%.2f", restaurantWishlist.getDishPrice()) + " KN");
        holder.DishDescription.setText(restaurantWishlist.getDishDescription());
    }

    public void setlWishlist(List<Wishlist>wishlist)
    {
        this.lWishlist = wishlist;
    }

    @Override
    public int getItemCount() {

        return lWishlist.size();
    }


    class WishlistDishVH extends RecyclerView.ViewHolder{
        private TextView DishName;
        private TextView DishPrice;
        private TextView DishDescription;
        private Button btnRemove;

        public WishlistDishVH(@NonNull View itemView) {
            super(itemView);

            DishName = itemView.findViewById(R.id.WishlistDishName);
            DishPrice = itemView.findViewById(R.id.WishlistDishPrice);
            DishDescription = itemView.findViewById(R.id.WishlistDishDescription);
            btnRemove = itemView.findViewById(R.id.ButtonDelete);

            btnRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    onButtonClickRemove.OnBtnClickRemove(lWishlist.get(position));
                }
            });
        }
    }

    public interface OnButtonClickListener
    {
        void OnBtnClickRemove(Wishlist wishlist);
    }

    public void SetOnClickListener(OnButtonClickListener onButtonClickListener)
    {
        this.onButtonClickRemove = onButtonClickListener;
    }

}
