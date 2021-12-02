package com.example.pizzaapp.backend;

import android.content.Intent;
import android.os.Parcelable;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.pizzaapp.R;


import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private ImageButton buttonDeluxe;
    private ImageButton buttonHawaiian;
    private ImageButton buttonPepperoni;
    private ImageButton buttonCurrentOrder;
    private ImageButton buttonStoreOrders;
    private EditText textPhoneNumber;
    public String phoneNumber ="";
    private static final int TEN = 10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonCurrentOrder = findViewById(R.id.imageButtonCurrentOrder);
        buttonStoreOrders = findViewById(R.id.imageButtonStoreOrders);
        buttonDeluxe = findViewById(R.id.imageButtonDeluxe);
        textPhoneNumber = findViewById(R.id.textPhoneNumber);

        if (!OrderProcessing.currentPhoneNumber.isEmpty()) {
            phoneNumber = OrderProcessing.currentPhoneNumber.get(0);
            textPhoneNumber.setText(phoneNumber);
        }
    }



    public void onClickDeluxe(View view) {
        if (phoneNumberHandler() == true) {
            OrderProcessing.currentPhoneNumber.add(phoneNumber);
            textPhoneNumber.setFocusable(false);
            Intent intent = new Intent(this, PizzaCustomizationActivity.class);
            intent.putExtra("message", "Deluxe Pizza");
            startActivity(intent);
        } else {
            Toast.makeText(MainActivity.this, "Enter phone number", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickHawaiian(View view){
        if (phoneNumberHandler() == true) {
            OrderProcessing.currentPhoneNumber.add(phoneNumber);
            textPhoneNumber.setFocusable(false);
            Intent intent = new Intent(this, PizzaCustomizationActivity.class);
            intent.putExtra("message", "Hawaiian Pizza");
            startActivity(intent);
        } else {
            Toast.makeText(MainActivity.this, "Enter phone number", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickPepperoni(View view){
        if (phoneNumberHandler() == true) {
            OrderProcessing.currentPhoneNumber.add(phoneNumber);
            textPhoneNumber.setFocusable(false);
            Intent intent = new Intent(this, PizzaCustomizationActivity.class);
            intent.putExtra("message", "Pepperoni Pizza");
            startActivity(intent);
        } else {
            Toast.makeText(MainActivity.this, "Enter phone number", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickCurrentOrder(View view){
        Intent redirect=new Intent(this,CurrentOrderActivity.class);
        startActivity(redirect);
    }

    private boolean phoneNumberHandler() {
        try {
            if (!textPhoneNumber.getText().equals("") & textPhoneNumber.getText().length() == TEN
                    & isNumber(textPhoneNumber.getText())) {
                 phoneNumber = String.valueOf(textPhoneNumber.getText());
                return true;
                }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public void setPhoneNum(boolean b) {
        textPhoneNumber.setFocusableInTouchMode(b);
        textPhoneNumber.setText("");
    }

    private boolean isNumber(Editable phoneNumber){
       try {
           Long.parseLong(String.valueOf(phoneNumber));
           return true;
       } catch (NumberFormatException e){
           return false;
       }
    }

    public void onClickStoreOrders(View v){
        Intent intent = new Intent( this, StoreOrdersActivity.class );
        startActivity(intent);

    }


}