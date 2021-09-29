package com.example.farmshop;


import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.query.predicate.QueryPredicate;
import com.amplifyframework.datastore.generated.model.Farm;
import com.amplifyframework.datastore.generated.model.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

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

            ImageButton deleteItem=itemView.findViewById(R.id.deletButton);
            deleteItem.setOnClickListener((v)->{

                Amplify.API.mutate(ModelMutation.delete(item),
                        result -> {
                            Log.i("MyAmplifyApp", "Todo with id: " + result.getData().getId());
                        },
                        error -> {
                            Log.e("MyAmplifyApp", "Create failed", error);
                        }
                        );
            });

        }
    }
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_cart_list,parent,false);
        ItemAdapter.ItemViewHolder itemViewHolder=new ItemAdapter.ItemViewHolder(view);
        return itemViewHolder;

    }
    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.item=allItem.get(position);
//        ImageView imageView=holder.itemView.findViewById(R.id.imageView);
        TextView title=holder.itemView.findViewById(R.id.titleProducts);
        TextView quntity=holder.itemView.findViewById(R.id.showQuntityafterClic);
        TextView price=holder.itemView.findViewById(R.id.priceProdects);

        title.setText(holder.item.getName());
        quntity.setText(holder.item.getQuantity()+" kg");
        price.setText("JD "+holder.item.getPrice());
    }

    @Override
    public int getItemCount() {
        return allItem.size();
    }
}
