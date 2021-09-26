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
import com.amplifyframework.datastore.generated.model.Item;
import com.amplifyframework.datastore.generated.model.User;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;

import java.util.ArrayList;

public class CartPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_page);
    }

    @Override
    protected void onStart() {
        super.onStart();
        BottomNavigationItemView cart=findViewById(R.id.page_1);
        cart.setOnClickListener((v)->{
            Intent startProduct=new Intent(CartPage.this,ProductPage.class);
            startActivity(startProduct);
        });
        renderOfProduct();
    }
    private void renderOfProduct(){
        RecyclerView allItemRecyclerView=findViewById(R.id.itemResycleview);
        Handler handler = new Handler(Looper.getMainLooper(),
                new Handler.Callback() {
                    @Override
                    public boolean handleMessage(@NonNull Message msg) {
                        allItemRecyclerView.getAdapter().notifyDataSetChanged();
                        return false;
                    }
                });
        ArrayList<Item> allItem=new ArrayList<Item>();
        Amplify.API.query(
                ModelQuery.list(Item.class),
                response -> {
                    for (Item item : response.getData()) {
                        if (item.getUserId().equals(Amplify.Auth.getCurrentUser().getUserId())){
                            Log.i("MyAmplifyApp", item.getName());
                            System.out.println("+++++++++++++++++++++++++++++");
                            System.out.println("00000000000000000000000000");
                            allItem.add(item);
                        }
                    }
                    handler.sendEmptyMessage(1);
                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );
        allItemRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),3));
        allItemRecyclerView.setAdapter(new ItemAdapter(allItem));
    }
}