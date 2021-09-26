package com.example.farmshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Button;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.auth.options.AuthSignOutOptions;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Product;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class ProductPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_page);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Button singOut=findViewById(R.id.singOutUser);
        singOut.setOnClickListener((v)->{
            signOut();
        });
        renderOfProduct();

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





    }
    private void signOut(){
        Amplify.Auth.signOut(
                AuthSignOutOptions.builder().globalSignOut(true).build(),
                () -> Log.i("AuthQuickstart", "Signed out globally"),
                error -> Log.e("AuthQuickstart", error.toString())
        );
        Intent intent=new Intent(ProductPage.this,Home.class);
        startActivity(intent);
    }

    private void renderOfProduct(){
        RecyclerView allProductRecyclerView=findViewById(R.id.recyclerViewProductOfUser);
        Handler handler = new Handler(Looper.getMainLooper(),
                new Handler.Callback() {
                    @Override
                    public boolean handleMessage(@NonNull Message msg) {
                        allProductRecyclerView.getAdapter().notifyDataSetChanged();
                        return false;
                    }
                });
        ArrayList<Product> allProduct=new ArrayList<Product>();
        Amplify.API.query(
                ModelQuery.list(Product.class),

                response -> {

                    for (Product product : response.getData()) {
                        Log.i("MyAmplifyApp", product.getName());
                        Log.i("MyAmplifyApp", product.getPrice());
                        allProduct.add(product);
                    }
                    handler.sendEmptyMessage(1);
                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );
        allProductRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),3));
        allProductRecyclerView.setAdapter(new ProductAdapter(allProduct));
    }
}