package com.example.farmshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProductPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_page);
    }

    @Override
    protected void onStart() {
        super.onStart();
        BottomNavigationItemView cart=findViewById(R.id.page_2);
        cart.setOnClickListener((v)->{
            Intent startCart=new Intent(ProductPage.this,CartPage.class);
            startActivity(startCart);
        });

    }
}