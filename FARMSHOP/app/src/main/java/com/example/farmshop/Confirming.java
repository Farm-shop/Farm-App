package com.example.farmshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Farm;
import com.amplifyframework.datastore.generated.model.User;

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
        String id= intent.getExtras().getString("Id");
        String phone= intent.getExtras().getString("Phone");
        Button user=findViewById(R.id.userType);
        user.setOnClickListener((b)->{
            User users = User.builder()
                    .userSignId(id)
                    .name(username)
                    .phone(phone)
                    .label("user")
                    .build();
                            Amplify.API.mutate(ModelMutation.create(users),
                                    response -> Log.i("MyAmplifyApp", "Todo with id: " + response.getData().getId()),
                                    error -> Log.e("MyAmplifyApp", "Create failed", error)
                            );
        });

        Button farmer=findViewById(R.id.farmerType);
        farmer.setOnClickListener((b)->{
            User users = User.builder()
                    .userSignId(id)
                    .name(username)
                    .phone(phone)
                    .label("farmer")
                    .build();
            Amplify.API.mutate(ModelMutation.create(users),
                    response ->Log.i("MyAmplifyApp", "Todo with id: " + response.getData().getId()),
                    error -> {
                        Log.e("MyAmplifyApp", "Create failed", error);
                    }
            );
            Farm farm = Farm.builder()
                    .userSignId(id)
                    .name(username)
                    .phone(phone)
                    .build();
            Amplify.API.mutate(ModelMutation.create(farm),
                    response ->Log.i("MyAmplifyApp", "Todo with id: " + response.getData().getId()),
                    error -> {
                        Log.e("MyAmplifyApp", "Create failed", error);
                    }
            );

        });
        confirm.setOnClickListener((v)->{
            Amplify.Auth.confirmSignUp(
                    username,
                    code.getText().toString(),
                    result -> {
                        Log.i("AuthQuickstart", result.isSignUpComplete() ? "Confirm signUp succeeded" : "Confirm sign up not complete");
//                        Intent backToSignIn = new Intent(Confirming.this, LogIn.class);
                        Intent backToSelectType = new Intent(Confirming.this, LogIn.class);
                        startActivity(backToSelectType);
                    },
                    error -> Log.e("AuthQuickstart", error.toString())
            );
        });

    }
}