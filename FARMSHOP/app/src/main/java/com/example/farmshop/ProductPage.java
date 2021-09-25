package com.example.farmshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.amplifyframework.auth.options.AuthSignOutOptions;
import com.amplifyframework.core.Amplify;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProductPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_page);
    }

    @Override
    protected void onStart() {
        super.onStart();

        BottomNavigationItemView cart=findViewById(R.id.page_2);
        Amplify.Auth.fetchAuthSession(
                result ->{
                    Log.i("AmplifyQuickstart", result.toString());
                    if (result.isSignedIn()){
                        Amplify.Auth.fetchUserAttributes(
                                attributes -> {
                                    Log.i("AuthDemo", "User attributes = " + attributes.toString());
                                },
                                error -> Log.e("AuthDemo", "Failed to fetch user attributes.", error)
                        );

                        cart.setOnClickListener((v)->{
                            Intent startCart=new Intent(ProductPage.this,CartPage.class);
                            startActivity(startCart);
                        });
                    }else {
                        cart.setOnClickListener((v)->{
                            Intent startCart=new Intent(ProductPage.this,LogIn.class);
                            startActivity(startCart);
                        });
                    }
                },
                error -> Log.e("AmplifyQuickstart", error.toString())
        );



        Button singOut=findViewById(R.id.singOutUser);
        singOut.setOnClickListener((v)->{
            Amplify.Auth.signOut(
                    AuthSignOutOptions.builder().globalSignOut(true).build(),
                    () -> Log.i("AuthQuickstart", "Signed out globally"),
                    error -> Log.e("AuthQuickstart", error.toString())
            );
            Intent intent=new Intent(ProductPage.this,Home.class);
            startActivity(intent);
        });

    }
}