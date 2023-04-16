package com.example.onetomany;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    List<Order>orderList;

    public  OrderAdapter(List<Order>orderList){
        this.orderList=orderList;
    }
    @NonNull
    @Override
    public OrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.ViewHolder holder, int position) {
        Order order =orderList.get(position);
        holder.textorderid.setText(String.valueOf(order.getId()));
        holder.textdetailname.setText(order.getDetails());

    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textorderid,textdetailname;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textorderid = (TextView)itemView.findViewById(R.id.disorderid);
            textdetailname = (TextView)itemView.findViewById(R.id.disproductname);
        }
    }
}
