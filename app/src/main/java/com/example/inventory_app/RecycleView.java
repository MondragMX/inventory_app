package com.example.inventory_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inventory_app.Config.Constant;
import com.example.inventory_app.Database.Database;
import com.example.inventory_app.Entity.Client;
import com.example.inventory_app.Entity.Product;

import java.util.List;

public class RecycleView extends AppCompatActivity {
    String data;
    Database db;
    private List<Product> listProducts;
    private List<Client> listClients;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);

        Intent i = getIntent();
        data = i.getStringExtra("DATA");
        db = Room.databaseBuilder(getApplicationContext(), Database.class, Constant.DB_NAME).allowMainThreadQueries().build();
        lv = (ListView)findViewById(R.id.list);


        if(data.equals("c")) {
            setTitle("Clients");
            int nProducts = db.clientDao().count();
            Toast.makeText(this, "Clients: " + nProducts , Toast.LENGTH_SHORT).show();
            loadClients();
        }
        else {
            setTitle("Products");
            int nProducts = db.productDao().count();
            Toast.makeText(this, "Products: " + nProducts , Toast.LENGTH_SHORT).show();
            loadProducts();
        }
    }

    public void loadClients() {
        listClients = db.clientDao().getAllClients();
        AdapterClient adapterClient = new AdapterClient(this);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(RecycleView.this, InfoClients.class);
                Long id = listClients.get(i).getId();
                intent.putExtra("ID", id.toString());
                startActivity(intent);
            }
        });

        lv.setAdapter(adapterClient);
    }

    public void loadProducts() {
        listProducts = db.productDao().getAllProducts();
        AdapterProduct adapterProduct = new AdapterProduct(this);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(RecycleView.this, InfoProduct.class);
                Long id = listProducts.get(i).getId();
                intent.putExtra("ID", id.toString());
                startActivity(intent);
            }
        });

        lv.setAdapter(adapterProduct);
    }

    public void addNewItem(View view) {
        if(data.equals("c")) {
            Intent i = new Intent(getApplicationContext(), NewClient.class);
            startActivity(i);
        }
        else {
            Intent i = new Intent(getApplicationContext(), NewProduct.class);
            startActivity(i);
        }
    }

    public void returnMain(View view) {
        startActivity(new Intent(RecycleView.this, MainActivity.class));
    }

    class AdapterProduct extends ArrayAdapter<Product> {
        AppCompatActivity appCompatActivity;

        public AdapterProduct(AppCompatActivity context) {
            super(context, R.layout.item, listProducts);
            appCompatActivity = context;
        }


        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = appCompatActivity.getLayoutInflater();
            View item = inflater.inflate(R.layout.item,null);

            TextView txtInfo = (TextView)item.findViewById(R.id.txtNameItem);
            String data = listProducts.get(position).getId() + ".   " + listProducts.get(position).getName();
            txtInfo.setText(data);

            return item;
        }
    }

    class AdapterClient extends ArrayAdapter<Client> {
        AppCompatActivity appCompatActivity;

        public AdapterClient(AppCompatActivity context) {
            super(context, R.layout.item, listClients);
            appCompatActivity = context;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = appCompatActivity.getLayoutInflater();
            View item = inflater.inflate(R.layout.item,null);

            TextView txtInfo = (TextView)item.findViewById(R.id.txtNameItem);
            String data = listClients.get(position).getId() + ". " + listClients.get(position).getName();
            txtInfo.setText(data);

            return item;
        }
    }

}
