package com.example.inventory_app.Entity;

import android.provider.BaseColumns;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = Product.TABLE_NAME)
public class Product {

    /** Name of the table **/
    public static final String TABLE_NAME = "product_table";
    public static final String COLUMN_NAME = "product";

    /** Name of the column id **/
    public static final String COLUMN_ID = BaseColumns._ID;

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true, name = COLUMN_ID)
    private long id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "stock")
    private String stock;

    @ColumnInfo(name = "cost")
    private String cost;

    public Product(long id, String name, String description, String stock, String cost) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.stock = stock;
        this.cost = cost;
    }

    public Product() {
        this.name = "";
        this.description = "";
        this.stock = "";
        this.cost = "";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
}
