package com.example.pizzaapp.backend;

import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.pizzaapp.R;


import java.util.LinkedHashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private ImageButton buttonDeluxe;
    private ImageButton buttonHawaiian;
    private ImageButton buttonPepperoni;
    private ImageButton buttonCurrentOrder;
    private ImageButton buttonStoreOrders;
    StoreOrders storeOrders = new StoreOrders();
    Order order = new Order(null);
    private final Set phoneNumbersSet = new LinkedHashSet();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        storeOrders = new StoreOrders();
        order = new Order(null);
        buttonCurrentOrder =  findViewById(R.id.imageButtonCurrentOrder);
        buttonStoreOrders = findViewById(R.id.imageButtonStoreOrders);
        buttonDeluxe = findViewById(R.id.imageButtonDeluxe);
        buttonDeluxe.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, PizzaCustomizationActivity.class);
            intent.putExtra("message", "Deluxe Pizza");
            startActivity(intent);
        });
        buttonHawaiian = findViewById(R.id.imageButtonHawaiian);
        buttonHawaiian.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, PizzaCustomizationActivity.class);
            intent.putExtra("message", "Hawaiian Pizza");
            startActivity(intent);
        });
        buttonPepperoni = findViewById(R.id.imageButtonPepperoni);
        buttonPepperoni.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, PizzaCustomizationActivity.class);
            intent.putExtra("message", "Pepperoni Pizza");
            startActivity(intent);
        });
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