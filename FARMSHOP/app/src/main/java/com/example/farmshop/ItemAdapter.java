package com.example.farmshop;


import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
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
            Button updateQuntity=itemView.findViewById(R.id.plusQuntity);
            updateQuntity.setOnClickListener((v)->{
                int numAdd= Integer.parseInt(item.getQuantity())+1;
                System.out.println("+++++++++++++++++++++++++++++"+numAdd);
                item.builder()
                        .userId(Amplify.Auth.getCurrentUser().getUserId())
                        .farmId(item.getFarmId())
                        .name(item.getName())
                        .price(item.getPrice())
                        .quantity(String.valueOf(numAdd))
                        .status("add")
                        .build();
                Amplify.API.mutate(ModelMutation.update(item),
                        result ->{
                            Log.i("MyAmplifyApp", "Todo with id: " + result.getData().getId());
                            System.out.println("00000000000000000000000000"+result.getData().getQuantity());
                        } ,
                        error -> {
                            Log.e("MyAmplifyApp", "Create failed", error);
                        }
                );
            });
            Button decreaseQuntity=itemView.findViewById(R.id.minusQuntity);
            decreaseQuntity.setOnClickListener((v)->{
                int numAdd= Integer.parseInt(item.getQuantity())-1;
                System.out.println("+++++++++++++++++++++++++++++"+numAdd);

                item.builder()
                        .userId(Amplify.Auth.getCurrentUser().getUserId())
                        .farmId(item.getFarmId())
                        .name(item.getName())
                        .price(item.getPrice())
                        .quantity(String.valueOf(numAdd))
                        .status("add")
                        .build();
                Amplify.API.mutate(ModelMutation.update(item),
                        result -> {
                            System.out.println("000000000000000000000)))"+item.getQuantity());
                            Log.i("MyAmplifyApp", "Todo with id: " + result.getData().getId());
                            System.out.println("00000000000000000000000000"+result.getData().getQuantity());
                        },
                        error -> {
                            Log.e("MyAmplifyApp", "Create failed", error);
                        }
                );
            });
//            Button deleteItem=itemView.findViewById(R.id.deleteItem);
//            deleteItem.setOnClickListener((v)->{
//                Item itemUpadte=Item.builder()
//                        .userId(Amplify.Auth.getCurrentUser().getUserId())
//                        .farmId(item.getFarmId())
//                        .name(item.getName())
//                        .price(item.getPrice())
//                        .quantity(item.getQuantity())
//                        .status("add")
//                        .build();
//                Amplify.API.mutate(ModelMutation.delete(itemUpadte));
//            });
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
                    System.out.println(response+"+++++++++++++++++++++++++++");

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
        TextView showQuntitty=holder.itemView.findViewById(R.id.showQuntityafterClic);
//        imageView.setImageBitmap(BitmapFactory.decodeFile(holder.product.getImage()));

        title.setText(holder.item.getName());
        getFarm(holder.item.getFarmId());
        System.out.println(ModelQuery.get(Farm.class,holder.item.getFarmId()).getQuery()+"___________________________________");
        System.out.println(ModelQuery.get(Farm.class,holder.item.getFarmId()).getContent()+"___________________________________");
        System.out.println(ModelQuery.get(Farm.class,holder.item.getFarmId()).getResponseType()+"___________________________________");
        System.out.println(ModelQuery.get(Farm.class,holder.item.getFarmId()).getVariables()+"___________________________________");
        farm.setText(farmName);
        quntity.setText(holder.item.getQuantity());
        showQuntitty.setText(holder.item.getQuantity());
        price.setText("JD "+holder.item.getPrice()+"/kg");
    }

    @Override
    public int getItemCount() {
        return allItem.size();
    }
}
