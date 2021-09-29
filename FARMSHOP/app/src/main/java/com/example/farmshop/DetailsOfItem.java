package com.example.farmshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Item;

public class DetailsOfItem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_of_item);

    }
    public float num=1;
    public float total=0;
    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        String price = intent.getExtras().getString("price");
        String nameing = intent.getExtras().getString("title");
        String user = intent.getExtras().getString("title");
        String farm = intent.getExtras().getString("title");
//        String quqntity="1";
        ImageView imageView=findViewById(R.id.goToProductPage);
        ImageView imageView2=findViewById(R.id.goToCart);
        ImageView plus=findViewById(R.id.imageButtonplus);
        ImageView mins=findViewById(R.id.imageButtonmins);
        TextView count=findViewById(R.id.countQuntity);
        TextView name=findViewById(R.id.productNamedetalis);
        TextView viewTo =findViewById(R.id.texttotalPrice);
        Button addTocart=findViewById(R.id.addtocartbacktohome);
        imageView.setOnClickListener((v)->{
            Intent intent1=new Intent(DetailsOfItem.this,ProductPage.class);
            startActivity(intent1);
        });
        imageView2.setOnClickListener((v)->{
            Intent intent2=new Intent(DetailsOfItem.this,CartPage.class);
            startActivity(intent2);
        });
        viewTo.setText(price);
        name.setText(nameing);
        plus.setOnClickListener((v)->{
            num++;
            System.out.println(num+"-------------------------------");
            total=Float.parseFloat(price)*num;
            System.out.println(total);
            viewTo.setText(String.valueOf(total));
            count.setText(String.valueOf(num));
        });
        mins.setOnClickListener((v)->{
            num--;
            total=Float.parseFloat(price)*num;
            viewTo.setText(String.valueOf(total));
            count.setText(String.valueOf(num));
        });
        addTocart.setOnClickListener((v)->{
            if(total==0){
                total=Float.parseFloat(price);
            }
                    Item item=Item.builder()
                                    .userId(user)
                                    .farmId(farm)
                                    .name(nameing)
                                    .price(String.valueOf(total))
                                    .quantity(String.valueOf(num))
                                    .image("image")
                                    .status("add")
                                    .build();
                            Amplify.API.mutate(ModelMutation.create(item),
                                    resulting -> {
                                        Log.i("MyAmplifyApp", "Todo with id: " + resulting.getData().getId());

                                    },
                                    error -> {
                                        Log.e("MyAmplifyApp", "Create failed", error);
                                    }
                            );
            Intent intent4=new Intent(DetailsOfItem.this,ProductPage.class);
            startActivity(intent4);

        });
//        button.setOnClickListener((v)->{
////        Amplify.Auth.fetchAuthSession(
////                result ->{
////                    Log.i("AmplifyQuickstart", result.toString());
////                    if (result.isSignedIn()){
////                        Amplify.Auth.fetchUserAttributes(
////                                attributes -> {
////                                    Log.i("AuthDemo", "User attributes = " + attributes.toString());
////                                },
////                                error -> Log.e("AuthDemo", "Failed to fetch user attributes.", error)
////                        );
////        Item item=Item.builder()
//                                    .userId(user)
//                                    .farmId(farm)
//                                    .name(nameing)
//                                    .price(price)
//                                    .quantity(quqntity)
//                                    .image("image")
//                                    .status("add")
//                                    .build();
//                            Amplify.API.mutate(ModelMutation.create(item),
//                                    resulting -> {
//                                        Log.i("MyAmplifyApp", "Todo with id: " + resulting.getData().getId());
//
//                                    },
//                                    error -> {
//                                        Log.e("MyAmplifyApp", "Create failed", error);
//                                    }
//                            );
////                            Intent intent4=new Intent(DetailsOfItem.this,ProductPage.class);
////                            startActivity(intent4);
////
////                    }else {
//                        Intent intent5=new Intent(DetailsOfItem.this,LogIn.class);
//                        startActivity(intent5);
////                    }
////                },
////                error -> Log.e("AmplifyQuickstart", error.toString())
////        );
//        });






    }
}