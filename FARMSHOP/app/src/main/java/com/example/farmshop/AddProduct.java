package com.example.farmshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class AddProduct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EditText nameProduct=findViewById(R.id.editNameProdectFarmer);
        EditText priceProduct=findViewById(R.id.editpriceProdect);
        Button addProduct=findViewById(R.id.buttonAddProdectToFarm);
        
    }
}