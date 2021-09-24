package com.example.farmshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        EditText name = findViewById(R.id.editName);
        EditText email = findViewById(R.id.emailInSignUpPage);
        EditText passWord = findViewById(R.id.passwordsignupediting);
        EditText phone = findViewById(R.id.editPhone);
        EditText location = findViewById(R.id.editLocation);

//        EditText longitude = findViewById(R.id.passWordInSignUpPage);
//        EditText latitude = findViewById(R.id.passWordInSignUpPage);


        Button button = findViewById(R.id.buttonSignUp);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUpMethod(name.getText().toString(), email.getText().toString(), passWord.getText().toString(),
                        phone.getText().toString(), location.getText().toString(), longitude.getText().toString(),
                        latitude.getText().toString()

                );
            }
        });
    }

    public void signUpMethod(String name, String email, String passWord, String phone, String location, String longitude, String latitude) {
        Intent intent = new Intent(com.example.farmshop.SignUp.this, ConfirmSignUp.class);

        AuthSignUpOptions options = AuthSignUpOptions.builder()
                .userAttribute(AuthUserAttributeKey.email(), email)
                .userAttribute(AuthUserAttributeKey.email(), phone)
                .userAttribute(AuthUserAttributeKey.email(), location)
                .userAttribute(AuthUserAttributeKey.email(), longitude)
                .userAttribute(AuthUserAttributeKey.email(), latitude)
                .build();
        Amplify.Auth.signUp(name, passWord, options,
                result -> {
                    startActivity(intent);
                    Log.i("AuthQuickStart", "Result: " + result.toString());
                },
                error -> Log.e("AuthQuickStart", "Sign up failed", error)
        );
    }
}

