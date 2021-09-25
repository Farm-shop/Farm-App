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
        FloatingActionButton addProduct=findViewById(R.id.addProduct);
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
                ModelQuery.list(Product.class),

                response -> {

                    for (Product product : response.getData()) {
                        Amplify.API.query(
                                ModelQuery.get(Farm.class, product.getFarmId()),
                                result -> {
                                    Log.i("MyAmplifyApp", ((Farm) result.getData()).getUserSignId());
                                    System.out.println("0000000000000000000000000000000000000000000000000");
                                    if(result.getData().getUserSignId().equals(Amplify.Auth.getCurrentUser().getUserId())){
                                        System.out.println(result.getData().getUserSignId().equals(Amplify.Auth.getCurrentUser().getUserId())+"mmmmmmmmmmmmmm");
                                        System.out.println("########################################");
                                        Log.i("MyAmplifyApp", product.getName());
                                        Log.i("MyAmplifyApp", product.getPrice());
                                        System.out.println(product.toString()+"loloololololooloooooolllll");
                                        allProduct.add(product);
                                    }
                                },
                                error -> Log.e("MyAmplifyApp", error.toString(), error)
                        );

                    }
                    System.out.println(allProduct);
                    System.out.println("------------------------------");
                    handler.sendEmptyMessage(1);
                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );
        allProductRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),3));
        allProductRecyclerView.setAdapter(new ProductAdapter(allProduct));
    }
//    private String farmUserId;
//    private void getFarm(String id) {
//        Amplify.API.query(
//                ModelQuery.get(Farm.class, id),
//                result -> {
//                    Log.i("MyAmplifyApp", ((Farm) result.getData()).getUserSignId());
//
//                },
//                error -> Log.e("MyAmplifyApp", error.toString(), error)
//        );
//    }
}