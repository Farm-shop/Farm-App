package com.example.farmshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.VideoView;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Button getStart=findViewById(R.id.getStarted);
        getStart.setOnClickListener((v)->{
            Intent starProductPage=new Intent(Home.this,ProductPage.class);
            startActivity(starProductPage);
        });

        Button button=findViewById(R.id.logIn);
        button.setPaintFlags(button.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        button.setOnClickListener((v)->{
            Intent intent=new Intent(Home.this,LogIn.class);
            startActivity(intent);
        });
//        Button button1=findViewById(R.id.button);
//        button1.setOnClickListener((v)->{
//            Intent intent=new Intent(Home.this,FarmActivity.class);
//            startActivity(intent);
//        });
    }
}