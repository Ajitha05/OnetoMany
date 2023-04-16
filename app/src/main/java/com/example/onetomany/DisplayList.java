package com.example.onetomany;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DisplayList extends AppCompatActivity {
    OrderDao orderDao;
    List<Order> orderList;

    private RecyclerView recyclerView;
    private OrderAdapter orderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_list);

        recyclerView = findViewById(R.id.recyclerView2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        DaoSession daoSession = ((MyApplication) getApplication()).getDaoSession();
//        List<Order>orderList = daoSession.getOrderDao().loadAll();




        Intent intent = getIntent();

        long customerId = intent.getLongExtra("CustomerID", 0);

        // Get the customer object using the customer id
        Customer customer = daoSession.getCustomerDao().load(customerId);


        if (customer != null) {
            // Get the orders for the customer
            List<Order> orderList = customer.getOrders();

            // Create the adapter with the filtered order list
            orderAdapter = new OrderAdapter(orderList);
            recyclerView.setAdapter(orderAdapter);
//        } else {
//            Toast.makeText(this, "Customer not found", Toast.LENGTH_SHORT).show();
        }
    }
}




