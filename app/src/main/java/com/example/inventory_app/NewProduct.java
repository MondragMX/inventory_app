package com.example.inventory_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.inventory_app.Config.Constant;
import com.example.inventory_app.Database.Database;
import com.example.inventory_app.Entity.Product;

public class NewProduct extends AppCompatActivity {

    EditText name, description, stock, cost;

    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Products");
        setContentView(R.layout.activity_new_product);

        name = (EditText)findViewById(R.id.txtNameP);
        description = (EditText)findViewById(R.id.txtDescP);
        stock = (EditText)findViewById(R.id.txtStock);
        cost = (EditText)findViewById(R.id.txtCost);

        db = Room.databaseBuilder(getApplicationContext(), Database.class, Constant.DB_NAME).allowMainThreadQueries().build();
    }

    public void insert(View view) {
        Product p = new Product();
        p.setName(name.getText().toString());
        p.setDescription(description.getText().toString());
        p.setStock(stock.getText().toString());
        p.setCost(cost.getText().toString());

        long result = db.productDao().insert(p);

        if(result > 0) {
            Toast.makeText(this, "New Product added to the DB", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(NewProduct.this, RecycleView.class).putExtra("DATA","p"));
        }
        else {
            Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show();
        }
    }
}