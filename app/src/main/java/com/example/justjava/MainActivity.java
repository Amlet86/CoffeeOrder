package com.example.justjava;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int currentNumberOfCoffees = 1;
    int pricePerCup = 5;
    boolean hasWhippedCream;
    boolean hasChocolate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method return string for display info of Whipped Cream topping depending on condition checkBox.
     */
    private String setHasWhippedCream() {
        CheckBox toppingCheckBox = (CheckBox) findViewById(R.id.checkbox_whippedcream);
        hasWhippedCream = toppingCheckBox.isChecked();
        return hasWhippedCream ? "\n Whipped cream" : "";
    }

    /**
     * This method return string for display info of Chocolate topping depending on condition checkBox.
     */
    private String setHasChocolate() {
        CheckBox toppingCheckBox = (CheckBox) findViewById(R.id.checkbox_chocolate);
        hasChocolate = toppingCheckBox.isChecked();
        return hasChocolate ? "\n Chocolate" : "";
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        currentNumberOfCoffees++;
        display(currentNumberOfCoffees);
        displayPrice();
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        if (currentNumberOfCoffees == 0)
            display(currentNumberOfCoffees);
        else {
            currentNumberOfCoffees--;
            display(currentNumberOfCoffees);
        }
        displayPrice();
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        createOrderSummary(currentNumberOfCoffees);
    }

    /**
     * This method displays the given price value on the screen.
     */
    private void displayPrice() {
        int totalPrice = calculatePrice();
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(totalPrice));
    }

    /**
     * This method calculate and return total price.
     */
    private int calculatePrice() {
        return currentNumberOfCoffees * pricePerCup;
    }

    /**
     * This method displays the given text and price value on the screen.
     */
    private void createOrderSummary(int number) {
        int totalPrice = calculatePrice();
        String message = "Quantity: " + number
                + setHasWhippedCream()
                + setHasChocolate()
                + "\nTotal: $" + totalPrice
                + "\n Thank you!";
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        priceTextView.setText(message);
        priceTextView.setTextColor(Color.RED);
    }

}