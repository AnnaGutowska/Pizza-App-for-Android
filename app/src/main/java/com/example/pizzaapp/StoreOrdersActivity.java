package com.example.pizzaapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.pizzaapp.backend.StoreOrders;

public class StoreOrdersActivity extends AppCompatActivity {

    private StoreOrders storeOrders;
    private ListView listViewStoreOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_orders_layout);
    }
}
