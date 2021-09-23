package com.example.farmshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CartPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_page);
    }

    @Override
    protected void onStart() {
        super.onStart();
        BottomNavigationItemView cart=findViewById(R.id.page_1);
        cart.setOnClickListener((v)->{
            Intent startProduct=new Intent(CartPage.this,ProductPage.class);
            startActivity(startProduct);
        });



    }
}