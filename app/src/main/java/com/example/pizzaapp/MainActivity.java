package com.example.pizzaapp;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.pizzaapp.backend.Order;
import com.example.pizzaapp.backend.StoreOrders;

import java.util.LinkedHashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private StoreOrders storeOrders;
    private Order order;
    protected static Set phoneNumbersSet = new LinkedHashSet();
    private ImageButton buttonDeluxe;
    private ImageButton buttonHawaiian;
    private ImageButton buttonPepperoni;
    private ImageButton buttonCurrentOrder;
    private ImageButton buttonStoreOrders;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        storeOrders = new StoreOrders();
        order = new Order(null);
        buttonDeluxe = findViewById(R.id.imageButtonDeluxe);
        buttonDeluxe.setOnClickListener(this);
        buttonHawaiian = findViewById(R.id.imageButtonHawaiian);
        buttonHawaiian.setOnClickListener(this);
        buttonPepperoni = findViewById(R.id.imageButtonPepperoni);
        buttonPepperoni.setOnClickListener(this);
        buttonCurrentOrder =  findViewById(R.id.imageButtonCurrentOrder);
        buttonStoreOrders = findViewById(R.id.imageButtonStoreOrders);
    }


     @Override
     public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, PizzaCustomizationActivity.class);
        startActivity(intent);
    }

    public void onClickStoreOrders(View v){
        Intent intent = new Intent( this, StoreOrdersActivity.class );
        startActivity(intent);
    }

    public void onClickCurrentOrder(View v){
        Intent intent = new Intent( this, CurrentOrderActivity.class );
        startActivity(intent);
    }
}