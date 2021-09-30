package com.example.farmshop;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Farm;
import com.amplifyframework.datastore.generated.model.Item;
import com.amplifyframework.datastore.generated.model.User;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ItemViewHolder> {
    List<Item> allItem=new ArrayList<>();

    public OrderAdapter(List<Item> allItem) {

        this.allItem = allItem;
    }




    public static class ItemViewHolder extends RecyclerView.ViewHolder{
        public Item item;
        View itemiew;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemiew=itemView;

        }
    }
    private String name;
    private String phone;
    private void getUserm(String id) {
        Amplify.API.query(
                ModelQuery.get(User.class, id),
                response -> {
                    Log.i("MyAmplifyApp", ((User) response.getData()).getId());
                    name=((User) response.getData()).getName();
                    phone=((User) response.getData()).getPhone();

                },
                error -> Log.e("MyAmplifyApp", error.toString(), error)
        );

    }
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_order,parent,false);
        OrderAdapter.ItemViewHolder itemViewHolder=new OrderAdapter.ItemViewHolder(view);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.item=allItem.get(position);
//        ImageView imageView=holder.itemView.findViewById(R.id.imageView);
        TextView title=holder.itemView.findViewById(R.id.titleProductsOrder);
        TextView quntity=holder.itemView.findViewById(R.id.showQuntityafterClicOrder);
        TextView price=holder.itemView.findViewById(R.id.priceProdectsOrder);
        TextView naming=holder.itemView.findViewById(R.id.textViewNameuser);
        TextView phoning=holder.itemView.findViewById(R.id.textViewphoneuser);

        title.setText(holder.item.getName());
        quntity.setText(holder.item.getQuantity()+" kg");
        price.setText("JD "+holder.item.getPrice());
        getUserm(holder.item.getUserId());
        naming.setText(name);
        phoning.setText(phone);
    }

    @Override
    public int getItemCount() {
        return allItem.size();
    }
}
