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

public class InfoProduct extends AppCompatActivity {

    EditText nameP, stock, cost, decP;

    long id;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_info_product);
        
        Intent i = getIntent();
        id = Long.parseLong(i.getStringExtra("ID"));
        db = Room.databaseBuilder(getApplicationContext(), Database.class, Constant.DB_NAME).allowMainThreadQueries().build();

        setTitle("Products");
        setContentView(R.layout.display_info_product);
        Product result = db.productDao().selectById(id);

        nameP = (EditText)findViewById(R.id.txtNameI);
        nameP.setText(result.getName());
        stock = (EditText)findViewById(R.id.txtStockI);
        stock.setText(result.getStock());
        cost = (EditText)findViewById(R.id.txtCostI);
        cost.setText(result.getCost());
        decP = (EditText)findViewById(R.id.txtDescI);
        decP.setText(result.getDescription());

    }

    public void saveData(View view) {
        Product p = new Product();
        p.setId(id);
        p.setName(nameP.getText().toString());
        p.setStock(stock.getText().toString());
        p.setCost(cost.getText().toString());
        p.setDescription(decP.getText().toString());

        int result = db.productDao().updateEntity(p);

        if(result > 0) {
            Toast.makeText(this, "Update with succes", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(InfoProduct.this, RecycleView.class).putExtra("DATA","p"));
        }
        else {
            Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteProduct(View view) {
        int result = db.productDao().deteleById(id);

        if(result > 0) {
            Toast.makeText(this, "Delete with succes", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(InfoProduct.this, RecycleView.class).putExtra("DATA","p"));
        }
        else {
            Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show();
        }
    }
}