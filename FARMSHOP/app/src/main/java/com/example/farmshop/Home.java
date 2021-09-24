package com.example.farmshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.VideoView;

import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;

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
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    //for code in authotincation
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == AWSCognitoAuthPlugin.WEB_UI_SIGN_IN_ACTIVITY_CODE) {
            Amplify.Auth.handleWebUISignInResponse(data);
        }
    }
}