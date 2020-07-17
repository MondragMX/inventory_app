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

public class NewClient extends AppCompatActivity {

    EditText name, phone, desc;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Clients");
        setContentView(R.layout.activity_new_client);

        name = (EditText)findViewById(R.id.txtNameC);
        phone = (EditText)findViewById(R.id.txtPhone);
        desc = (EditText)findViewById(R.id.txtDescC);

        db = Room.databaseBuilder(getApplicationContext(), Database.class, Constant.DB_NAME).allowMainThreadQueries().build();
    }

    public void insert(View view) {
        Client c = new Client();
        c.setName(name.getText().toString());
        c.setPhone(phone.getText().toString());
        c.setDescription(desc.getText().toString());

        long result = db.clientDao().insert(c);

        if(result > 0) {
            Toast.makeText(this, "New Client added to the DB", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(NewClient.this, RecycleView.class).putExtra("DATA","c"));
        }
        else {
            Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show();
        }
    }
}