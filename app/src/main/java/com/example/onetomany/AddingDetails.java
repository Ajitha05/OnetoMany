package com.example.onetomany;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AddingDetails extends AppCompatActivity implements View.OnClickListener {
    EditText cusid, customername,oid,dname;
    LinearLayout layout;
    Button add, subbtn;
    DaoSession daoSession;
    List<String> detaillist = new ArrayList<>();
    List<Order> orderList;
    ArrayList<Order> orderArrayList = new ArrayList<>();


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_details);

        daoSession = ((MyApplication) getApplication()).getDaoSession();

        cusid = findViewById(R.id.customerid);
        customername = findViewById(R.id.cusname);
        add = findViewById(R.id.addbtn);
        oid = findViewById(R.id.orderid);
        dname = findViewById(R.id.detailsname);
        subbtn = findViewById(R.id.subtbtn);
        layout = findViewById(R.id.linear_list);

        add.setOnClickListener(this);

        detaillist.add("choose products");
        detaillist.add("Bag");
        detaillist.add("Laptop");
        detaillist.add("Mobile");
        detaillist.add("Book");
        detaillist.add("Pen");
        detaillist.add("Watch");
        detaillist.add("Tab");

        subbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String customerIdString = cusid.getText().toString();
                long customerIdLong = Long.parseLong(customerIdString);
                String namestring = customername.getText().toString();

                Customer customer = new Customer();
                customer.setId(customerIdLong);
                customer.setName(namestring);
                daoSession.getCustomerDao().insertOrReplace(customer);



                for (int i = 0; i < layout.getChildCount(); i++) {
                    View orderview = layout.getChildAt(i);


                    Order order = new Order();
                    orderview.findViewById(R.id.orderid);
                    orderview.findViewById(R.id.detailsname);
                    AppCompatSpinner detailspinner =(AppCompatSpinner) orderview.findViewById(R.id.detailsname);
                    String selectedDetail = detailspinner.getSelectedItem().toString();
                    EditText orderIDet = (EditText) orderview.findViewById(R.id.orderid);
                    String orderIDstring = orderIDet.getText().toString();


                    long orderID = Long.parseLong(orderIDstring);
                    order.setId(orderID);
                    long customerID = Long.parseLong(customerIdString);
                    order.setDetails(detaillist.get(i));
                    order.setCustomerId(customerIdLong);
                    daoSession.getOrderDao().insert(order);

                    if(detailspinner.getSelectedItemPosition()!=0){
                        order.setDetails(detaillist.get(detailspinner.getSelectedItemPosition()));
                    }else {
                        break;
                    }


                }

                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);

            }
        });
    }


        private boolean checkIfValidAndRead () {
            orderList.clear();
            boolean result = true;
            return result;



        }



        @Override
        public void onClick (View v){
            Toast.makeText(this, "Function starts", Toast.LENGTH_SHORT).show();


            switch (v.getId()) {

                case R.id.addbtn:
                    Toast.makeText(this, "click add button", Toast.LENGTH_SHORT).show();

                    addView();

                    break;


            }

        }



        private void addView () {
            final View orderview = getLayoutInflater().inflate(R.layout.rows_add, null, false);
            EditText editText = (EditText) orderview.findViewById(R.id.orderid);
            AppCompatSpinner spinnerTeam = (AppCompatSpinner) orderview.findViewById(R.id.detailsname);
            ImageView imageClose = (ImageView) orderview.findViewById(R.id.image_remove);

            ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, detaillist);
            spinnerTeam.setAdapter(arrayAdapter);
            Toast.makeText(this, "select details", Toast.LENGTH_SHORT).show();

            imageClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeView(orderview);


                }
            });
            layout.addView(orderview);

        }
        private void removeView (View view){

            layout.removeView(view);

        }

}


