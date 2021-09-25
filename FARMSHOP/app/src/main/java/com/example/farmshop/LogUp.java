package com.example.farmshop;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.User;

public class LogUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_up);

        EditText username = findViewById(R.id.editName);
        EditText email = findViewById(R.id.emailInSignUpPage);
        EditText password = findViewById(R.id.latatuideView);
        EditText phone = findViewById(R.id.editPhonNumber);
        Button button = findViewById(R.id.buttonSignUp);
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
