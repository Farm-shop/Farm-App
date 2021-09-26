package com.example.farmshop;


import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Farm;
import com.amplifyframework.datastore.generated.model.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder>{
    List<Item> allItem=new ArrayList<>();

    public ItemAdapter(List<Item> allItem) {
        this.allItem = allItem;
    }



    public static class ItemViewHolder extends RecyclerView.ViewHolder{
        public Item item;
        View itemiew;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemiew=itemView;
//            itemView.findViewById(R.id.fragmentProduct).setOnClickListener((v)->{
//                Intent intent=new Intent(itemView.getContext(),DetailsOfItem.class);
//
//                intent.putExtra("name",item.getName());
//                intent.putExtra("price",farmName);
//                intent.putExtra("ingredient",restu.ingredient);
//                itemView.getContext().startActivity(intent);
//            });
        }
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_list,parent,false);
//        ProductAdapter.ProductViewHolder productViewHolder=new ProductAdapter.ProductViewHolder(view);
        ItemAdapter.ItemViewHolder itemViewHolder=new ItemAdapter.ItemViewHolder(view);
        return itemViewHolder;

    }
    private String farmName;
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
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.item=allItem.get(position);
//        ImageView imageView=holder.itemView.findViewById(R.id.imageView);
        TextView title=holder.itemView.findViewById(R.id.titleProducts);
        TextView farm=holder.itemView.findViewById(R.id.farmProducts);
        TextView quntity=holder.itemView.findViewById(R.id.showQuntty);
        TextView price=holder.itemView.findViewById(R.id.priceProdects);
//        imageView.setImageBitmap(BitmapFactory.decodeFile(holder.product.getImage()));

        title.setText(holder.item.getName());
        getFarm(holder.item.getFarmId());
        farm.setText(farmName);
        quntity.setText(holder.item.getQuantity());
        price.setText("JD "+holder.item.getPrice()+"/kg");
    }

    @Override
    public int getItemCount() {
        return allItem.size();
    }
}
