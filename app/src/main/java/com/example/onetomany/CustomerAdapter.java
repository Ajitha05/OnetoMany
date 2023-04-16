package com.example.onetomany;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.ViewHolder> {
    List<Customer> customerList;

    public CustomerAdapter(List<Customer> customerList) {
        this.customerList = customerList;
    }

    @NonNull
    @Override
    public CustomerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.item_row,parent,false);
        ViewHolder viewHolder=new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerAdapter.ViewHolder holder, int position) {
        final Customer customer = customerList.get(position);
        holder.getName().setText(customer.getName().trim());
        holder.getId().setText(String.valueOf(customer.getId()));


        holder.constraintLayout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Toast.makeText(view.getContext(), customer.getName(), Toast.LENGTH_SHORT).show();

                Intent i = new Intent(view.getContext(),DisplayList.class);

                List<Order> orderList =  customer.getOrders();




                i.putExtra("CustomerID",customer.getId());
                view.getContext().startActivity(i);


            }

        });

    }




    @Override
    public int getItemCount() {
        return customerList.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public void setName(TextView name) {
            this.name = name;

        }
        public void setId(TextView id) {
            this.id = id;
        }


        public TextView getName() {
            return name;

        }
        public TextView getId() {
            return id;

        }

        private TextView name,id;
        public ConstraintLayout constraintLayout;

        public ViewHolder( View view) {
            super(view);
            this.name=(TextView) view.findViewById(R.id.labelItemName);
            this.id =(TextView) view.findViewById(R.id.useridtv);
            constraintLayout=(ConstraintLayout) view.findViewById(R.id.itemrow);

        }
    }


}