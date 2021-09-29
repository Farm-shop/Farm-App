package com.example.farmshop;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.User;
import com.amplifyframework.storage.s3.AWSS3StoragePlugin;

public class LogUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_up);

        try {
            Amplify.addPlugin(new AWSApiPlugin());
//            Amplify.addPlugin(new AWSDataStorePlugin());
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
            Amplify.addPlugin(new AWSS3StoragePlugin());
            Amplify.configure(getApplicationContext());
            Log.i("MyAmplifyApp", "Initialized Amplify");
            System.out.println("try[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[");
        } catch (AmplifyException error) {
            Log.e("MyAmplifyApp", "Could not initialize Amplify", error);
            System.out.println("try[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[");
        }


        EditText username = findViewById(R.id.nameSignUp);
        EditText email = findViewById(R.id.emailInSignUpPage);
        EditText password = findViewById(R.id.passwordSignup);
        EditText phone = findViewById(R.id.editPhonNumber);
        Button button = findViewById(R.id.signUpButton);
        Button transition=findViewById(R.id.signInonTransitionUp);
        ImageView imageView=findViewById(R.id.imagebackLogUp);
        imageView.setOnClickListener((v)->{
            Intent intent =new Intent(LogUp.this,Home.class);
            startActivity(intent);
        });
        transition.setOnClickListener((v)->{
            Intent intent=new Intent(LogUp.this,LogIn.class);
            startActivity(intent);
        });
        button.setOnClickListener((v)->{
            AuthSignUpOptions options = AuthSignUpOptions.builder()
                    .userAttribute(AuthUserAttributeKey.email(), email.getText().toString())
                    .build();
            Amplify.Auth.signUp(username.getText().toString(), password.getText().toString(), options,
                    result -> {
                        Log.i("AuthQuickStart", "Result: " + result.toString());
                        System.out.println(result.getUser().getUserId());
                        Intent goToConfirmation = new Intent(LogUp.this, Confirming.class);
                        goToConfirmation.putExtra("Name", username.getText().toString());
                        goToConfirmation.putExtra("Id", result.getUser().getUserId());
                        goToConfirmation.putExtra("Phone", phone.getText().toString());

                        startActivity(goToConfirmation);
                    },
                    error -> {
                        Log.e("AuthQuickStart", "Sign up failed", error);
                        runOnUiThread(new Runnable() {
                            public void run() {
                                @SuppressLint("WrongConstant") final Toast toast = Toast.makeText(getApplicationContext(), "\"USER ALREADY EXIST  OR Password or Username does not match requirements\"", 5000);
                                toast.show();
                            }
                        });
                    }
            );
        });
    }
}
