package com.example.farmshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FarmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FloatingActionButton addProduct=findViewById(R.id.addProduct);
        addProduct.setOnClickListener((v)->{
            Intent intent=new Intent(FarmActivity.this,AddProduct.class);
            startActivity(intent);
        });
    }
}