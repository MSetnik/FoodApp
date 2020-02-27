package com.example.foodapp.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.foodapp.Entity.Restaurant;
import com.example.foodapp.Entity.Wishlist;
import com.example.foodapp.FoodAppViewModel;
import com.example.foodapp.Model.RestaurantWishlist;
import com.example.foodapp.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.WishListVH> {
    private List<RestaurantWishlist>lRestWishlist = new ArrayList<>();
    //private List<Restaurant>lRestaurants = new ArrayList<>();
    private Context context;
    private FoodAppViewModel viewModel;
    private OnButtonClickListenerVanjski onButtonClickListenerVanjski;
    private CallRestaurantListener callRestaurantListener;
    private List<String> lRestPhone = new ArrayList<>();

    public WishlistAdapter(Context context, FoodAppViewModel viewModel) {
        this.context = context;
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public WishListVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.wishlist, parent, false);
        WishListVH holder = new WishListVH(itemview);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final WishListVH holder, final int position) {
        RestaurantWishlist restaurant = lRestWishlist.get(position);
        String Dostava = "Dostava";
        if(restaurant.getDelivery() == 1)
        {
            holder.RestaurantDelivery.setTextColor(Color.GREEN);
        }
        else{
            holder.RestaurantDelivery.setTextColor(Color.RED);
        }
        holder.RestaurantName.setText(restaurant.getRestaurantName());
        holder.RestaurantPhone.setText(restaurant.getPhone());
        holder.RestaurantDelivery.setText(Dostava);
        String sRestPhone = restaurant.getPhone();
        lRestPhone.add(sRestPhone);

        WishlistDishAdapter wishlistDishAdapter = new WishlistDishAdapter();
        wishlistDishAdapter.setlWishlist(restaurant.getlRestaurantWishlist());
        holder.DishRecycler.setLayoutManager(new LinearLayoutManager(context));
        holder.DishRecycler.setAdapter(wishlistDishAdapter);
        wishlistDishAdapter.SetOnClickListener(new WishlistDishAdapter.OnButtonClickListener() {
            @Override
            public void OnBtnClickRemove(Wishlist wishlist) {
                //viewModel.DeleteSelectedItem(wishlist);
                //Toast.makeText(context, "Jelo obrisano", Toast.LENGTH_SHORT).show();
                onButtonClickListenerVanjski.OnBtnClickRemove(wishlist);
            }
        });

    }

    @Override
    public int getItemCount() {
        return lRestWishlist.size();
    }

    public void setlWishlist(List<RestaurantWishlist> restWishlist)
    {
        this.lRestWishlist = restWishlist;
        notifyDataSetChanged();
    }

   /* public void setlRestaurants(List<Restaurant> restaurants){
        this.lRestaurants = restaurants;
        notifyDataSetChanged();
    }*/



    class WishListVH extends RecyclerView.ViewHolder
    {
        TextView RestaurantName;
        TextView RestaurantPhone;
        TextView RestaurantDelivery;
        RecyclerView DishRecycler;

        public WishListVH(@NonNull View itemView) {
            super(itemView);

            RestaurantName = itemView.findViewById(R.id.RestaurantNameWishlist);
            RestaurantPhone = itemView.findViewById(R.id.RestaurantWishlistPhone);
            RestaurantDelivery = itemView.findViewById(R.id.RestaurantWishlistDelivery);
            DishRecycler = itemView.findViewById(R.id.recyclerViewWishlistDish);

            RestaurantPhone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    callRestaurantListener.OnPhoneClick(lRestPhone.get(position));
                }
            });
        }
    }

    public interface CallRestaurantListener
    {
        void OnPhoneClick(String tel);
    }

    public void SetOnClickListenerPhone(CallRestaurantListener callRestaurantListener){
        this.callRestaurantListener = callRestaurantListener;
    }

    public interface OnButtonClickListenerVanjski
    {
        void OnBtnClickRemove(Wishlist wishlist);
    }

    public void SetOnClickListenerVanjski(OnButtonClickListenerVanjski onButtonClickListener)
    {
        this.onButtonClickListenerVanjski = onButtonClickListener;
    }
}
