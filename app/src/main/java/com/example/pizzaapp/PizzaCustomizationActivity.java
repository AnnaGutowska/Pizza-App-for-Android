package com.example.pizzaapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.pizzaapp.backend.Order;

public class PizzaCustomizationActivity extends Activity {

    private Order order;
    TextView temporary;
    ImageView pizzaPhoto;

    CheckBox CheckBoxBlackOlives;
    CheckBox CheckBoxHam;
    CheckBox CheckBoxCheese;
    CheckBox CheckBoxSausage;
    CheckBox CheckBoxPineapple;
    CheckBox CheckBoxGreenPepper;
    CheckBox CheckBoxOnion;
    CheckBox CheckBoxChicken;
    CheckBox CheckBoxBeef;
    CheckBox CheckBoxMushroom;
    CheckBox CheckBoxPepperoni;

    TextView textViewTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cutomize_layout);
        temporary = findViewById(R.id.temporary);
        temporary.setText(getIntent().getStringExtra("message"));
        pizzaPhoto = findViewById(R.id.pizzaPhoto);
        textViewTotal = findViewById(R.id.textViewTotal);

        CheckBoxBlackOlives = findViewById(R.id.checkBoxBlackOlives);
        CheckBoxHam = findViewById(R.id.checkBoxHam);
        CheckBoxCheese = findViewById(R.id.checkBoxCheese);
        CheckBoxSausage = findViewById(R.id.checkBoxSausage);
        CheckBoxPineapple = findViewById(R.id.checkBoxPineapple);
        CheckBoxGreenPepper = findViewById(R.id.checkBoxGreenPepper);
        CheckBoxOnion = findViewById(R.id.checkBoxOnion);
        CheckBoxChicken = findViewById(R.id.checkBoxChicken);
        CheckBoxBeef = findViewById(R.id.checkBoxBeef);
        CheckBoxMushroom = findViewById(R.id.checkBoxMushroom);
        CheckBoxPepperoni = findViewById(R.id.checkBoxPepperoni);

        if (temporary.getText().equals("Deluxe Pizza")){
            pizzaPhoto.setImageResource(R.drawable.deluxepic);
            setToppingsForDeluxe();
        } else if (temporary.getText().equals("Hawaiian Pizza")) {
            pizzaPhoto.setImageResource(R.drawable.hawaiianpic);
            setToppingsForHawaiian();
        } else {
            pizzaPhoto.setImageResource(R.drawable.pepperonipic);
            setToppingsForPepperoni();
        }



    }

    private void setToppingsForPepperoni() {
        CheckBoxPepperoni.setChecked(true);
    }

    private void setToppingsForHawaiian() {
        CheckBoxPineapple.setChecked(true);
        CheckBoxCheese.setChecked(true);
    }

    private void setToppingsForDeluxe() {
        CheckBoxGreenPepper.setChecked(true);
        CheckBoxSausage.setChecked(true);
        CheckBoxOnion.setChecked(true);
        CheckBoxPepperoni.setChecked(true);
        CheckBoxMushroom.setChecked(true);
    }


}
