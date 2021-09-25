package com.example.farmshop;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
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
        private static String type="Ahmad";

    void signIn(String username, String password) {
        Amplify.Auth.signIn(
                username,
                password,
                result  -> {
                    Log.i(TAG, "signIn: worked " + result .toString());
                    Handler handler = new Handler(Looper.getMainLooper(),
                            new Handler.Callback() {
                                @Override
                                public boolean handleMessage(@NonNull Message msg) {
                                    return false;
                                }
                            });
                    Amplify.API.query(
                            ModelQuery.list(User.class, User.USER_SIGN_ID.contains(Amplify.Auth.getCurrentUser().getUserId())),
                            response -> {
                                for (User user : response.getData()) {
                                    Log.i("MyAmplifyApp", user.getLabel());

                                    type=user.getLabel();
                                    if(type.equals("user")){
                                        Intent goToMain = new Intent(LogIn.this, ProductPage.class);
                                        startActivity(goToMain);
                                    }else if (type.equals("farmer")){
                                        Intent goToMain = new Intent(LogIn.this, FarmActivity.class);
                                        startActivity(goToMain);
                                    }else {
                                        Intent goToMain = new Intent(LogIn.this, LogUp.class);
                                        startActivity(goToMain);
                                    }
                                }
                                handler.sendEmptyMessage(1);
                            },
                            error -> Log.e("MyAmplifyApp", "Query failure", error)
                    );


                },
                error -> Log.e(TAG, "signIn: failed" + error.toString()));
    }
}