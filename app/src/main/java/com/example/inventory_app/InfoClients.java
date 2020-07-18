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
import com.example.inventory_app.Entity.Client;
import com.example.inventory_app.Entity.Product;

public class InfoClients extends AppCompatActivity {

    EditText nameC, phone, decC;

    long id;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_clients);

        Intent i = getIntent();
        id = Long.parseLong(i.getStringExtra("ID"));
        db = Room.databaseBuilder(getApplicationContext(), Database.class, Constant.DB_NAME).allowMainThreadQueries().build();

        setTitle("Clients");
        setContentView(R.layout.activity_info_clients);
        Client result = db.clientDao().selectById(id);

        nameC = (EditText)findViewById(R.id.txtNameI2);
        nameC.setText(result.getName());
        phone = (EditText)findViewById(R.id.txtPhoneI);
        phone.setText(result.getPhone());
        decC = (EditText)findViewById(R.id.txtDescI2);
        decC.setText(result.getDescription());
    }

    public void saveData(View view) {
        Client c = new Client();
        c.setId(id);
        c.setName(nameC.getText().toString());
        c.setPhone(phone.getText().toString());
        c.setDescription(decC.getText().toString());

        int result = db.clientDao().updateEntity(c);

        if(result > 0) {
            Toast.makeText(this, "Update with succes", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(InfoClients.this, RecycleView.class).putExtra("DATA","c"));
        }
        else {
            Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteProduct(View view) {
        int result = db.clientDao().deteleById(id);

        if(result > 0) {
            Toast.makeText(this, "Delete with succes", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(InfoClients.this, RecycleView.class).putExtra("DATA","c"));
        }
        else {
            Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show();
        }
    }
}