package com.example.farmshop;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;

public class SignIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);


        try {
//            Amplify.addPlugin(new AWSS3StoragePlugin());
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.addPlugin(new AWSDataStorePlugin());

            // Add this line, to include the Auth plugin.
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
            Amplify.configure(getApplicationContext());

            Log.i("Tutorial", "Initialized Amplify");
        } catch (AmplifyException failure) {
            Log.e("Tutorial", "Could not initialize Amplify", failure);
        }

        //for checking
        Amplify.Auth.fetchAuthSession(
                result -> Log.i("AmplifyQuickstart", result.toString()),
                error -> Log.e("AmplifyQuickstart", error.toString())
        );

        Intent intent = new Intent(SignIn.this, Home.class);
        Amplify.Auth.fetchAuthSession(
                result -> {
                    Log.i("AmplifyQuickstart", "successfully signIn");
                },
                error -> Log.e("AmplifyQuickstart", "You need to signIn")
        );
        Button signUp = findViewById(R.id.signUpButton);

        Button signInButton = findViewById(R.id.signInButtonInSignPage);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignIn.this, SignUp.class);
                startActivity(intent);
            }
        });

        EditText name = findViewById(R.id.nameSignIn);
        EditText passWord = findViewById(R.id.passwordSignIn);


        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                signInMethod(name.getText().toString(), passWord.getText().toString());
            }
        });


    }



    public void signInMethod(String userName, String passWord) {
        Intent intent = new Intent(SignIn.this, Home.class);

        System.out.println(userName);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(SignIn.this);
        SharedPreferences.Editor sharedPreferenceEditor = sharedPreferences.edit();

        sharedPreferenceEditor.putString("userRegester", userName);
        sharedPreferenceEditor.apply();


        Amplify.Auth.signIn(
                userName,
                passWord,
                result -> {
                    if (result.isSignInComplete()) {
                        handler2();
                        startActivity(intent);
                    } else {
                        System.out.println("ERROR!");
                    }
                },
                error -> {
                    handler();
                    Log.e("AuthQuickstart", error.toString());
                }
        );



    }

    public void handler() {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), "Incorrect username or password!", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void handler2() {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), "Success!", Toast.LENGTH_LONG).show();
            }
        });
    }


}