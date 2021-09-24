package com.example.farmshop;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.datastore.generated.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends  RecyclerView.Adapter<ProductAdapter.ProductViewHolder>{
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
        }
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_product,parent,false);
        ProductViewHolder productViewHolder=new ProductViewHolder(view);
        return productViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.product=allProduct.get(position);
    }

    @Override
    public int getItemCount() {
        return allProduct.size();
    }
}
