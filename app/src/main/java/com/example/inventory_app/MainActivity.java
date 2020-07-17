package com.example.inventory_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    static final String CLIENT_DATA = "c";
    static final String PRODUCT_DATA = "p";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("App");
        setContentView(R.layout.activity_main);
    }

    public void client(View view) {
        Intent i = new Intent(getApplicationContext(), RecycleView.class);
        i.putExtra("DATA", CLIENT_DATA);
        startActivity(i);
    }

    public void product(View view) {
        Intent i = new Intent(getApplicationContext(), RecycleView.class);
        i.putExtra("DATA", PRODUCT_DATA);
        startActivity(i);
    }

}