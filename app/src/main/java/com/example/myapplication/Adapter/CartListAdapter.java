package com.example.myapplication.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.Domain.FoodDomain;
import com.example.myapplication.Helper.ManagmentCart;
import com.example.myapplication.Interface.ChangeNumberItemsListener;
import com.example.myapplication.R;

import java.util.ArrayList;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> {
    ArrayList<FoodDomain> ListFoodSelected;
    private final ManagmentCart managementCart;
    ChangeNumberItemsListener changeNumberItemsListener;

    public CartListAdapter(ArrayList<FoodDomain> ListFoodSelected, Context context, ChangeNumberItemsListener changeNumberItemsListener){
        this.ListFoodSelected = ListFoodSelected;
        managementCart=new ManagmentCart(context);
        this.changeNumberItemsListener=changeNumberItemsListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart, parent, false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        holder.title.setText(ListFoodSelected.get(position).getTitle());
        holder.feeEachItem.setText("Rs. "+ListFoodSelected.get(position).getFee());
        holder.totalEachItem.setText("Rs. "+Math.round((ListFoodSelected.get(position).getNumberInCart()*ListFoodSelected.get(position).getFee())));
        holder.num.setText(String.valueOf(ListFoodSelected.get(position).getNumberInCart()));


        int drawableResourceId=holder.itemView.getContext().getResources().getIdentifier(ListFoodSelected.get(position).getPic(), "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.pic);

        holder.plusItem.setOnClickListener(v -> managementCart.plusNumberFood(ListFoodSelected, position, () -> {
            notifyDataSetChanged();
            changeNumberItemsListener.changed();
        }));

        holder.minusItem.setOnClickListener(v -> managementCart.minusNumberFood(ListFoodSelected, position, () -> {
            notifyDataSetChanged();
            changeNumberItemsListener.changed();
        }));
    }

    @Override
    public int getItemCount(){ return ListFoodSelected.size(); }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title, feeEachItem;
        ImageView pic,plusItem,minusItem;
        TextView totalEachItem, num;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            title=itemView.findViewById(R.id.titleTxt);
            pic=itemView.findViewById(R.id.pic);
            feeEachItem=itemView.findViewById(R.id.feeEachItem);
            totalEachItem=itemView.findViewById(R.id.totalEachItem);
            plusItem=itemView.findViewById(R.id.plusCartBtn);
            minusItem=itemView.findViewById(R.id.minusCartBtn);
            num=itemView.findViewById(R.id.numberItemTxt);


        }
    }
}
