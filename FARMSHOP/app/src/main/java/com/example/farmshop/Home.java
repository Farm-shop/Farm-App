package com.example.farmshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.VideoView;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.storage.s3.AWSS3StoragePlugin;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
//        VideoView videoView=findViewById(R.id.homeVideo);
//        String videopath ="android.resource://com.example.farmshop/"+R.raw.home ;
//        Uri uri = Uri.parse(videopath);
//        videoView.setVideoURI(uri);
//        videoView.start();


        try {
            Amplify.addPlugin(new AWSApiPlugin());//
//            Amplify.addPlugin(new AWSDataStorePlugin());
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
//            Amplify.addPlugin(new AWSS3StoragePlugin());
            Amplify.configure(getApplicationContext());
            Log.i("MyAmplifyApp", "Initialized Amplify");
            System.out.println("try[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[");
        } catch (AmplifyException error) {
            Log.e("MyAmplifyApp", "Could not initialize Amplify", error);
            System.out.println("=========================false");
        }
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
        button.setOnClickListener((v)->{
            Intent intent=new Intent(Home.this,LogIn.class);
            startActivity(intent);
        });
//        Button button1=findViewById(R.id.button3);
//        button1.setOnClickListener((v)->{
//            Intent intent=new Intent(Home.this,AddProduct.class);
//            startActivity(intent);
//        });
    }
}