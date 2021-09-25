package com.example.farmshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.User;

import java.io.InputStream;

public class SelectTypeUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_type_user);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Button user=findViewById(R.id.userType);
        user.setOnClickListener((v)->{
            User users = User.builder()
                    .label("user")
                    .build();
            Amplify.DataStore.save(users,
                    saved -> Log.i("MyAmplifyApp", "Saved a post."),
                    failure -> Log.e("MyAmplifyApp", "Save failed.", failure)
            );
            Intent backToSignIn=new Intent(SelectTypeUser.this,LogIn.class);
            startActivity(backToSignIn);
        });
        Button farmer=findViewById(R.id.farmerType);
        farmer.setOnClickListener((v)->{
            User users = User.builder()
                    .label("farmer")
                    .build();
            Amplify.DataStore.save(users,
                    saved ->{
                        Log.i("MyAmplifyApp", "Saved a post.");
//                        users.getId()
                    },
                    failure -> Log.e("MyAmplifyApp", "Save failed.", failure)
            );
            Intent backToSignIn=new Intent(SelectTypeUser.this,LogIn.class);
            startActivity(backToSignIn);
        });
    }
}