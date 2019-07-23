package com.example.justjava;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int numberOfCoffees = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        numberOfCoffees++;
        display(numberOfCoffees);
        displayPrice(numberOfCoffees);
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        if (numberOfCoffees == 0)
            display(numberOfCoffees);
        else {
            numberOfCoffees--;
            display(numberOfCoffees);
        }
        displayPrice(numberOfCoffees);
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
        displayMessage(numberOfCoffees);
    }

    /**
     * This method displays the given price value on the screen.
     */
    private void displayPrice(int number) {
        int totalPrice = number * 5;
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(totalPrice));
    }

    /**
     * This method displays the given text and price value on the screen.
     */
    private void displayMessage(int number) {
        int totalPrice = number * 5;
        String message = "Thank you for order \n" + number + " coffees.\n Total price: $" + totalPrice;
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }

}