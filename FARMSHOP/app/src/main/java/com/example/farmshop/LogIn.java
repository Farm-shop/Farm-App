package com.example.farmshop;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.User;

public class LogIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        Button signUp = findViewById(R.id.signUpButton);
        Button signInButton = findViewById(R.id.signInButtonInSignPage);
        EditText username = findViewById(R.id.nameSignIn);
        EditText password = findViewById(R.id.passwordSignIn);

        signUp.setOnClickListener((v)->{
            Intent goToSingUpBtn = new Intent(LogIn.this, LogUp.class);
            startActivity(goToSingUpBtn);
        });

        signInButton.setOnClickListener((v)->{

            Amplify.Auth.fetchAuthSession(
                    result ->{
                        Log.i("AmplifyQuickstart", result.toString());
                    },
                    error -> Log.e("AmplifyQuickstart", error.toString())
            );

            signIn(username.getText().toString(), password.getText().toString());


        });
    }
        private static String type;
        private void getUserType(String id) {
        Amplify.API.query(
                ModelQuery.get(User.class, id),
                response -> {
                    Log.i("MyAmplifyApp", ((User) response.getData()).getLabel());
                    type=((User) response.getData()).getLabel();
                },
                error -> Log.e("MyAmplifyApp", error.toString(), error)
        );
    }
    void signIn(String username, String password) {
        Amplify.Auth.signIn(
                username,
                password,
                result  -> {
                    Log.i(TAG, "signIn: worked " + result .toString());
                    Intent goToMain = new Intent(LogIn.this, ProductPage.class);
                    startActivity(goToMain);
                },
                error -> Log.e(TAG, "signIn: failed" + error.toString()));
    }
}