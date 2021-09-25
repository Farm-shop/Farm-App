package com.example.farmshop;

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

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Farm;
import com.amplifyframework.datastore.generated.model.Product;
import com.amplifyframework.datastore.generated.model.User;

public class AddProduct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        System.out.println(")))00000000000000000000");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Button addProduct=findViewById(R.id.buttonAddProdectToFarm);
        addProduct.setOnClickListener((v)->{
           addProdect();
        });
    }

    private void addProdect(){
         EditText nameProduct=findViewById(R.id.editNameProdectFarmer);
         EditText priceProduct=findViewById(R.id.editpriceProdect);

         Handler handler = new Handler(Looper.getMainLooper(),
                 new Handler.Callback() {
                     @Override
                     public boolean handleMessage(@NonNull Message msg) {
                         return false;
                     }
                 });
         Amplify.API.query(
                 ModelQuery.list(Farm.class, Farm.USER_SIGN_ID.contains(Amplify.Auth.getCurrentUser().getUserId())),
                 response -> {
                     for (Farm farm : response.getData()) {
                         Log.i("MyAmplifyApp", farm.getId());
                         Product product = Product.builder()
                                 .farmId(farm.getId())
                                 .name(nameProduct.getText().toString())
                                 .price(priceProduct.getText().toString())
                                 .build();
                         Amplify.API.mutate(ModelMutation.create(product),
                                 result -> Log.i("MyAmplifyApp", "Todo with id: " + result.getData().getId()),
                                 error -> {
                                     Log.e("MyAmplifyApp", "Create failed", error);
                                 }
                         );
                     }
                     handler.sendEmptyMessage(1);
                 },
                 error -> Log.e("MyAmplifyApp", "Query failure", error)
         );


         Intent intent=new Intent(AddProduct.this,FarmActivity.class);
         startActivity(intent);
    }
}