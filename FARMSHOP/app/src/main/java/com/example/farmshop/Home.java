package com.example.farmshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.VideoView;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        VideoView videoView=findViewById(R.id.homeVideo);
        String videopath ="android.resource://com.example.farmshop/"+R.raw.home ;
        Uri uri = Uri.parse(videopath);
        videoView.setVideoURI(uri);
        videoView.start();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Button getStart=findViewById(R.id.getStarted);
        getStart.setOnClickListener((v)->{
            Intent starProductPage=new Intent(Home.this,ProductPage.class);
            startActivity(starProductPage);
        });

        Button button=findViewById(R.id.button2);
        button.setOnClickListener((v)->{
            Intent intent=new Intent(Home.this,LogIn.class);
            startActivity(intent);
        });
        Button button1=findViewById(R.id.button3);
        button1.setOnClickListener((v)->{
            Intent intent=new Intent(Home.this,FarmActivity.class);
            startActivity(intent);
        });
    }
}