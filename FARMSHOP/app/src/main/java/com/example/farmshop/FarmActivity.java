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
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Farm;
import com.amplifyframework.datastore.generated.model.Product;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class FarmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Button addProduct=findViewById(R.id.addProduct);
        addProduct.setOnClickListener((v)->{
            Intent intent=new Intent(FarmActivity.this,AddProduct.class);
            startActivity(intent);
        });

        renderOfProduct();
    }
    private void renderOfProduct(){
        RecyclerView allProductRecyclerView=findViewById(R.id.recyclerViewProductOfFarm);
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
                ModelQuery.list(Farm.class),
                response -> {
                    for (Farm farm : response.getData()) {
                        if (farm.getUserSignId().equals(Amplify.Auth.getCurrentUser().getUserId())){
                            Log.i("MyAmplifyApp", farm.getName());
                            for (Product product : farm.getProducts()) {
                                allProduct.add(product);
                            }
                        }
                    }
                    handler.sendEmptyMessage(1);
                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );
        allProductRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),3));
        allProductRecyclerView.setAdapter(new ProductAdapter(allProduct));
    }
}