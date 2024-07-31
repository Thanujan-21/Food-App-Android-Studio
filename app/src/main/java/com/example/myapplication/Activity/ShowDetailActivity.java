package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.Domain.FoodDomain;
import com.example.myapplication.Helper.ManagmentCart;
import com.example.myapplication.R;

public class ShowDetailActivity extends AppCompatActivity {
    private TextView addToCartBtn;
    private TextView titleTxt, feeTxt, descriptionTxt, numberOrderTxt, totalPriceTxt, starTxt, caloryTxt, timeTxt;
    private ImageView plusBtn, minusBtn, picFood;
    private FoodDomain object;
    private int numberOrder = 1;
    private ManagmentCart managmentCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);

        managmentCart=new ManagmentCart(this);

        iniView();
        getBundle();
    }

    private void getBundle() {
        object=(FoodDomain)getIntent().getSerializableExtra("object");

        int drawableResourceId=this.getResources().getIdentifier(object.getPic(),"drawable", this.getPackageName());
        Glide.with(this).load(drawableResourceId).into(picFood);

        titleTxt.setText(object.getTitle());
        feeTxt.setText("Rs. "+object.getFee());
        descriptionTxt.setText(object.getDescription());
        numberOrderTxt.setText(String.valueOf(numberOrder));
        caloryTxt.setText(object.getCalories()+" calories");
        timeTxt.setText(object.getTime()+" minutes");
        starTxt.setText(object.getStar()+"");
        totalPriceTxt.setText("Rs. "+numberOrder* object.getFee());

        plusBtn.setOnClickListener(v -> {
            numberOrder=numberOrder+1;
            numberOrderTxt.setText(String.valueOf(numberOrder));
            totalPriceTxt.setText("Rs. "+numberOrder* object.getFee());
        });

        minusBtn.setOnClickListener(v -> {
            if (numberOrder>1){
                numberOrder=numberOrder-1;
            }
            numberOrderTxt.setText(String.valueOf(numberOrder));
            totalPriceTxt.setText("Rs. "+numberOrder* object.getFee());
        });

        addToCartBtn.setOnClickListener(v -> {
            object.setNumberInCart(numberOrder);
            managmentCart.insertFood(object);
        });


    }

    private void iniView() {
        addToCartBtn=findViewById(R.id.addToCartBtn);
        titleTxt=findViewById(R.id.titleTxt);
        feeTxt=findViewById(R.id.priceTxt);
        descriptionTxt=findViewById(R.id.descriptionTxt);
        numberOrderTxt=findViewById(R.id.numberItemTxt);
        plusBtn=findViewById(R.id.plusCartBtn);
        minusBtn=findViewById(R.id.minusCartBtn);
        picFood=findViewById(R.id.foodPic);
        totalPriceTxt=findViewById(R.id.totalPriceTxt);
        starTxt=findViewById(R.id.starTxt);
        caloryTxt=findViewById(R.id.caloriesTxt);
        timeTxt=findViewById(R.id.timeTxt);
    }
}