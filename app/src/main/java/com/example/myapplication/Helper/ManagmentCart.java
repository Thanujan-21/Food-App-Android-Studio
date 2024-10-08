package com.example.myapplication.Helper;

import android.content.Context;
import android.widget.Toast;

import com.example.myapplication.Domain.FoodDomain;
import com.example.myapplication.Interface.ChangeNumberItemsListener;

import java.util.ArrayList;

public class ManagmentCart {
    private Context context;
    private TinyDB tinyDB;

    public ManagmentCart(Context context) {
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }
    public void insertFood(FoodDomain item){
        ArrayList<FoodDomain> listFood=getListCart();
        boolean existAlready=false;
        int n=0;
        for (int i = 0; i < listFood.size(); i++) {
            if (listFood.get(i).getTitle().equals(item.getTitle())) {
                existAlready = true;
                n=i;
                break;

            }
        }

        if(existAlready){
            listFood.get(n).setNumberInCart(item.getNumberInCart());
        }else {
            listFood.add(item);
        }
        tinyDB.putListObject("CardList",listFood);
        Toast.makeText(context,"Added to your Cart",Toast.LENGTH_SHORT).show();
    }
    public ArrayList<FoodDomain>getListCart(){
        return tinyDB.getListObject("cardList");
    }
    public void minusNumberFood(ArrayList<FoodDomain>Listfood, int position, ChangeNumberItemsListener changeNumberItemsListener){
        if(Listfood.get(position).getNumberInCart()==1){
            Listfood.remove(position);
        } else {
            Listfood.get(position). setNumberInCart(Listfood.get(position).getNumberInCart() - 1);
        }
        tinyDB.putListObject(  "CardList",Listfood);
        changeNumberItemsListener.changed();
    }
    public void plusNumberFood(ArrayList<FoodDomain> Listfood, int position, ChangeNumberItemsListener changeNumberItemsListener){
        Listfood.get(position).setNumberInCart(Listfood.get(position).getNumberInCart()+1);
        tinyDB.putListObject(  "CardList",Listfood);
        changeNumberItemsListener.changed();
    }
    public Double getTotalFee() {
        ArrayList<FoodDomain> Listfood = getListCart();
        double fee = 0;
        for (int i = 0; i < Listfood.size(); i++) {
            fee = fee + (Listfood.get(i).getFee() * Listfood.get(i).getNumberInCart());
        }
        return fee;
    }
}
