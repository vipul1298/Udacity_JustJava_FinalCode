package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int quantity =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public String createOrderSummary(int num,boolean addWhipped,boolean addChocolate,String value){
        String PriceMessage =("Name:" + value + "\nadded a whipped cream?"+ addWhipped + "\nadded a chocolate?"+ addChocolate + "\nQuantity:"+ quantity + "\nTotal:$" + num +"\nThankyou!");
        return PriceMessage;
    }


    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox checkBox = (CheckBox) findViewById(R.id.whippedCream);
        boolean hasWhipped=checkBox.isChecked();
        CheckBox chkbox = (CheckBox) findViewById(R.id.chocolate);
        boolean hasChecked=chkbox.isChecked();
        EditText text = (EditText)findViewById(R.id.name);
        String value = text.getText().toString();
        int price = calculatePrice(quantity,hasWhipped,hasChecked);
         String priceMessage=createOrderSummary(price,hasWhipped,hasChecked,value);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for " + value);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }


    }


    public void increment(View view){
        if(quantity>=100){
            displayQuantity(100);
        }else {
            quantity = quantity + 1;
            displayQuantity(quantity);
        }
    }


    public void decrement(View view){
        if (quantity<=0){
            displayQuantity(0);
        }
        else{
            quantity = quantity-1;
            displayQuantity(quantity);
        }
        }


           /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    /**
     * This method displays the given price on the screen.
     */
//    private void displayPrice(int number) {
//        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
//        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
//    }

    private int calculatePrice(int quantity,boolean whipped,boolean chocolate) {
        int basePrice = 5;
        if(whipped){
            basePrice+=1;
        }
        if(chocolate){
            basePrice +=2;
        }
        return quantity * basePrice;
    }



}