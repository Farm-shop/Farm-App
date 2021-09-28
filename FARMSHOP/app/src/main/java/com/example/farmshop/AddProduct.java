package com.example.farmshop;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
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

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class AddProduct extends AppCompatActivity {
    EditText nameProduct;


    EditText priceProduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        System.out.println(")))00000000000000000000");
        nameProduct=findViewById(R.id.editNameProdectFarm);
        priceProduct=findViewById(R.id.editpriceProduct);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Button addProduct=findViewById(R.id.buttonAddProdectToFarm);
        addProduct.setOnClickListener((v)->{
           addProdect();
        });
        Button addImage=findViewById(R.id.addImage);
        addImage.setOnClickListener((v)->{
            Intent chooseFile=new Intent(Intent.ACTION_GET_CONTENT);
            chooseFile.setType("*/*");
            chooseFile=Intent.createChooser(chooseFile,"Choose a file");
            startActivityForResult(chooseFile,1234);
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String key=Amplify.Auth.getCurrentUser().getUserId()+nameProduct.getText().toString();
        File exampleFile = new File(getApplicationContext().getFilesDir(), "title");
        try {
            InputStream inputStream=getContentResolver().openInputStream(data.getData());
            OutputStream outputStream=new FileOutputStream(exampleFile);
            byte[]buf=new byte[1024];
            int len;
            while ((len=inputStream.read(buf))>0){
                outputStream.write(buf,0,len);
            }
            inputStream.close();
            outputStream.close();

        } catch (Exception exception) {
            Log.e("MyAmplifyApp", "Upload failed", exception);
        }

        Amplify.Storage.uploadFile(
                key,
                exampleFile,
                result -> Log.i("MyAmplifyApp", "Successfully uploaded: " + result.getKey()),
                storageFailure -> Log.e("MyAmplifyApp", "Upload failed", storageFailure)
        );
    }
   String images;
    private void addProdect(){
        String key=Amplify.Auth.getCurrentUser().getUserId()+nameProduct.getText().toString();
        Amplify.Storage.downloadFile(
                key,
                new File(getApplicationContext().getFilesDir() + "/download.jpg"),
                result -> {
                    Log.i("MyAmplifyApp", "Successfully downloaded: " + result.getFile().getName());
                    images =result.getFile().getPath();
                },
                error -> Log.e("MyAmplifyApp",  "Download Failure", error)
        );
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