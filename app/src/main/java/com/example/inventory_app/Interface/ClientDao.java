package com.example.inventory_app.Interface;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.inventory_app.Entity.Client;
import com.example.inventory_app.Entity.Product;

import java.util.List;

@Dao
public interface ClientDao {

    @Query("SELECT COUNT(*) FROM " + Client.TABLE_NAME)
    int count();

    @Query("SELECT * FROM " + Client.TABLE_NAME)
    List<Client> getAllClients();

    @Insert
    void insertAll(Client ... clients);

    @Query("DELETE FROM " + Client.TABLE_NAME + " WHERE " + Client.COLUMN_ID + " = :id")
    int deteleById(long id);

    @Query("SELECT * FROM " + Client.TABLE_NAME + " WHERE " + Client.COLUMN_ID + " = :id")
    Client selectById(long id);

    @Update
    int updateEntity(Client c);

    @Insert
    long insert(Client c);
}
