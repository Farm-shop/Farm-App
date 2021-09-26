package com.example.farmshop;

import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Farm;
import com.amplifyframework.datastore.generated.model.Item;
import com.amplifyframework.datastore.generated.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends  RecyclerView.Adapter<ProductAdapter.ProductViewHolder>{
    private String farmName;
    List<Product>allProduct=new ArrayList<>();
    public ProductAdapter(List<Product> allProduct) {
        this.allProduct = allProduct;
    }



    public static class ProductViewHolder extends RecyclerView.ViewHolder{
        public Product product;
        View itemView;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView=itemView;
            Button addToCart=itemView.findViewById(R.id.addToCartButton);
            addToCart.setOnClickListener((v)->{

                Item item=Item.builder()
                        .userId(Amplify.Auth.getCurrentUser().getUserId())
                        .farmId(product.getFarmId())
                        .name(product.getName())
                        .price(product.getPrice())
                        .quantity("1")
                        .status("add")
                        .build();
                Amplify.API.mutate(ModelMutation.create(item),
                        result -> Log.i("MyAmplifyApp", "Todo with id: " + result.getData().getId()),
                        error -> {
                            Log.e("MyAmplifyApp", "Create failed", error);
                        }
                );
            });
        }
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_product,parent,false);
        ProductViewHolder productViewHolder=new ProductViewHolder(view);
        return productViewHolder;
    }
    private void getFarm(String id) {
        Amplify.API.query(
                ModelQuery.get(Farm.class, id),
                response -> {
                    Log.i("MyAmplifyApp", ((Farm) response.getData()).getName());
                    farmName=((Farm) response.getData()).getName();
                },
                error -> Log.e("MyAmplifyApp", error.toString(), error)
        );
    }
    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.product=allProduct.get(position);
//        ImageView imageView=holder.itemView.findViewById(R.id.imageView);
        TextView title=holder.itemView.findViewById(R.id.titleProduct);
        TextView farm=holder.itemView.findViewById(R.id.farmProduct);
        TextView price=holder.itemView.findViewById(R.id.priceProdect);
//        imageView.setImageBitmap(BitmapFactory.decodeFile(holder.product.getImage()));

        title.setText(holder.product.getName());
        getFarm(holder.product.getFarmId());
        farm.setText(farmName);
        price.setText("JD "+holder.product.getPrice()+"/kg");
    }



    @Override
    public int getItemCount() {
        return allProduct.size();
    }
}
