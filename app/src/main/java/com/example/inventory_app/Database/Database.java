package com.example.inventory_app.Database;

import androidx.room.RoomDatabase;

import com.example.inventory_app.Entity.Client;
import com.example.inventory_app.Entity.Product;
import com.example.inventory_app.Interface.ClientDao;
import com.example.inventory_app.Interface.ProductDao;

@androidx.room.Database(entities = {Client.class, Product.class}, version = 1)
public abstract class Database extends RoomDatabase {

    @SuppressWarnings("WeakerAcces")
    public abstract ClientDao clientDao();

    public abstract ProductDao productDao();

    private static Database sInstance;
}
