package com.example.inventory_app.Interface;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.inventory_app.Entity.Product;

import java.util.List;

@Dao
public interface ProductDao {
    @Query("SELECT COUNT(*) FROM " + Product.TABLE_NAME)
    int count();

    @Query("SELECT * FROM " + Product.TABLE_NAME)
    List<Product> getAllProducts();

    @Insert
    void insertAll(Product ... products);

    @Query("DELETE FROM " + Product.TABLE_NAME + " WHERE " + Product.COLUMN_ID + " = :id")
    int deteleById(long id);

    @Query("SELECT * FROM " + Product.TABLE_NAME + " WHERE " + Product.COLUMN_ID + " = :id")
    Product selectById(long id);

    @Update
    int updateEntity(Product p);

    @Insert
    long insert(Product p);
}
