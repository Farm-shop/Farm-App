package com.example.farmshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsOfItem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_of_item);
    }
    public int num=1;
    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        String price = intent.getExtras().getString("price");
        String nameing = intent.getExtras().getString("title");
        ImageView plus=findViewById(R.id.imageButtonplus);
        ImageView mins=findViewById(R.id.imageButtonmins);
        TextView count=findViewById(R.id.countQuntity);
        TextView name=findViewById(R.id.productNamedetalis);
        TextView viewTo =findViewById(R.id.texttotalPrice);
        Button addTocart=findViewById(R.id.addtocartbacktohome);
        viewTo.setText(price);
        name.setText(nameing);
        plus.setOnClickListener((v)->{
            num++;
            int total=Integer.parseInt(price)*num;
            viewTo.setText(String.valueOf(total));
            count.setText(String.valueOf(num));
        });
        mins.setOnClickListener((v)->{
            num--;
            int total=Integer.parseInt(price)*num;
            viewTo.setText(String.valueOf(total));
            count.setText(String.valueOf(num));
        });
        addTocart.setOnClickListener((v)->{

        });
    }
}