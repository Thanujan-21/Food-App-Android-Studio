package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.myapplication.Adapter.CartListAdapter;
import com.example.myapplication.Helper.ManagmentCart;
import com.example.myapplication.Interface.ChangeNumberItemsListener;
import com.example.myapplication.R;

public class CartActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    private ManagmentCart managmentCart;
    private TextView totalFeeTxt, taxTxt, deliveryTxt, totalTxt, emptyTxt;
    private double tax;
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        managmentCart=new ManagmentCart(this);
        initView();
        initList();
        bottomNavigation();
        calculateCart();
    }
    private void bottomNavigation() {
        LinearLayout homeBtn=findViewById(R.id.homeBtn);
        LinearLayout cartBtn=findViewById(R.id.cartBtn);

        homeBtn.setOnClickListener(v -> startActivity(new Intent(CartActivity.this, MainActivity.class)));

        cartBtn.setOnClickListener(v -> startActivity(new Intent(CartActivity.this, CartActivity.class)));

    }

    private void initList() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter=new CartListAdapter(managmentCart.getListCart(), this, new ChangeNumberItemsListener() {
            @Override
            public void changed() {
                calculateCart();
            }
        });
        recyclerViewList.setAdapter(adapter);
        if(managmentCart.getListCart().isEmpty()){
            emptyTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        }else{
            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }

    private void calculateCart() {
        double percentageTax=0.02;
        double delivery=200;

        tax=Math.round((managmentCart.getTotalFee()*percentageTax)*100)/100;
        double total=Math.round((managmentCart.getTotalFee()+tax+delivery)*100)/100;
        double itemTotal=Math.round(managmentCart.getTotalFee()*100)/100;

        totalFeeTxt.setText("Rs."+itemTotal);
        taxTxt.setText("Rs."+tax);
        deliveryTxt.setText("Rs."+delivery);
        totalTxt.setText("Rs."+total);
    }

    private void initView(){
        totalFeeTxt=findViewById(R.id.totalfeeTxt);
        taxTxt=findViewById(R.id.taxTxt);
        deliveryTxt=findViewById(R.id.deliveryTxt);
        totalTxt=findViewById(R.id.totalTxt);
        recyclerViewList=findViewById(R.id.view);
        scrollView=findViewById(R.id.scrollView);
        emptyTxt=findViewById(R.id.emptyTxt);
    }

}