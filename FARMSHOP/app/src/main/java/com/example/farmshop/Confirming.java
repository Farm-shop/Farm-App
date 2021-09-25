package com.example.farmshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.amplifyframework.core.Amplify;

public class Confirming extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirming);

        EditText code= findViewById(R.id.verification);
        Button confirm = findViewById(R.id.confirmation);

        //EditText username = findViewById(R.id.username);
        Intent intent = getIntent();
        String username = intent.getExtras().getString("Name");
        confirm.setOnClickListener((v)->{
            Amplify.Auth.confirmSignUp(
                    username,
                    code.getText().toString(),
                    result -> {
                        Log.i("AuthQuickstart", result.isSignUpComplete() ? "Confirm signUp succeeded" : "Confirm sign up not complete");
//                        Intent backToSignIn = new Intent(Confirming.this, LogIn.class);
                        Intent backToSelectType = new Intent(Confirming.this, SelectTypeUser.class);
                        startActivity(backToSelectType);
                    },
                    error -> Log.e("AuthQuickstart", error.toString())
            );
        });

    }
}