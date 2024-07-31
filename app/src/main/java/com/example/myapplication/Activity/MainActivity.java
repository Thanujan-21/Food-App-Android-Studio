package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.myapplication.Adapter.CategoryAdapter;
import com.example.myapplication.Adapter.RecommendedAdapter;
import com.example.myapplication.Domain.CategoryDomain;
import com.example.myapplication.Domain.FoodDomain;
import com.example.myapplication.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
private RecyclerView.Adapter adapter,adapter2;
private RecyclerView recyclerViewCategoriesList,recyclerViewPopularList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewCategoriesList();
        recyclerViewPopularList();
        bottomNavigation();
    }

    private void bottomNavigation() {
        LinearLayout homeBtn=findViewById(R.id.homeBtn);
        LinearLayout cartBtn=findViewById(R.id.cartBtn);

        homeBtn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, MainActivity.class)));

        cartBtn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, CartActivity.class)));

    }

    private void recyclerViewPopularList() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPopularList = findViewById(R.id.view2);
        recyclerViewPopularList.setLayoutManager((linearLayoutManager));

        ArrayList<FoodDomain> foodList = new ArrayList<>();
        foodList.add(new FoodDomain("Pepperoni pizza", "pizza1", "slice pepproni, Mozzarella cheese, fresh oregano, ground black pepper, pizza sauce", 2500.0, 5, 20, 1000));
        foodList.add(new FoodDomain("Burger", "burger", "Beef, Gouda Cheese, Special sauce, Lettuce, tomato", 1500.0, 4, 18, 1000));
        foodList.add(new FoodDomain("Vegetable pizza", "pizza3", "Olive oil, vegetable oil, pitted Kalamata, cherry tomatoes, fresh oregano, basil", 1900.0, 3, 15, 500));

        adapter2= new RecommendedAdapter(foodList);
        recyclerViewPopularList.setAdapter(adapter2);
    }






    private void recyclerViewCategoriesList() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewCategoriesList = findViewById(R.id.view1);
        recyclerViewCategoriesList.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryDomain> CategoryList = new ArrayList<>();
        CategoryList.add(new CategoryDomain("Pizza","cat_1"));
        CategoryList.add(new CategoryDomain("Burger","cat_2"));
        CategoryList.add(new CategoryDomain("Hotdog","cat_3"));
        CategoryList.add(new CategoryDomain("Drink","cat_4"));
        CategoryList.add(new CategoryDomain("Donut","cat_5"));

        adapter= new CategoryAdapter(CategoryList);
        recyclerViewCategoriesList.setAdapter(adapter);

    }
}