package com.samyak.cofapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int no_of_orders=0;
    final private int price_per_coffee = 120; //in Rupees


    Button plus_quantity,order_button,minus_quantity;
    TextView quantity_display;
    EditText nameinput;

    CheckBox whippedcream,chocolate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        plus_quantity = (Button) findViewById(R.id.plusbutton);
        minus_quantity = (Button) findViewById(R.id.minusbutton);
        order_button = (Button) findViewById(R.id.orderbutton);

        quantity_display = (TextView)findViewById(R.id.quantitytext);

        nameinput = (EditText)findViewById(R.id.nameinputtext);

        quantity_display.setText(String.valueOf(no_of_orders));

        whippedcream = (CheckBox) findViewById(R.id.Whipped_Cream_Checkbox);
        chocolate = (CheckBox) findViewById(R.id.Chocolate_Checkbox);

        minus_quantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(no_of_orders>0) {
                    no_of_orders--;
                }
                quantity_display.setText(String.valueOf(no_of_orders));

            }
        });
        plus_quantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                no_of_orders++;
                quantity_display.setText(String.valueOf(no_of_orders));
            }
        });


        order_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int temp_price=price_per_coffee;
                boolean wantwhippedcream=false;
                boolean wantchocolate=false;

                if (chocolate.isChecked()){
                    wantchocolate=true;
                    temp_price+=20;
                }

                if (whippedcream.isChecked()){
                    wantwhippedcream = true;
                    temp_price+=30;
                }

                int total_price = temp_price*no_of_orders;
                String finalname = String.valueOf(nameinput.getText());
                String name = "Name :"+ finalname;
                String quantity = "Quantity :"+ no_of_orders;
                String whippedcream = "Whipped Cream: " + wantwhippedcream;
                String chocolate= "Chocolate: " + wantchocolate;
                String price = "Total Price = \u20B9"+total_price;
                String summary = name+"\n"+quantity+"\n"+whippedcream+"\n"+chocolate+"\n"+price;

                if(no_of_orders>0) {
                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setData(Uri.parse("mailto:thesamcoffeecompany@gmail.com"));
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Order Details");
                    intent.putExtra(Intent.EXTRA_TEXT, summary);
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    }
                }else {
                    Toast.makeText(MainActivity.this,"Quantity should be more than Zero",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}