package com.example.inventory_app.Entity;

import android.provider.BaseColumns;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = Client.TABLE_NAME)
public class Client {

    public static final String TABLE_NAME = "client_table";
    public static final String COLUMN_NAME = "client";

    public static final String COLUMN_ID = BaseColumns._ID;

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true, name = COLUMN_ID)
    private long id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "phone")
    private String phone;

    @ColumnInfo(name = "description")
    private String description;

    public Client() {
        this.name = "";
        this.phone = "";
        this.description = "";
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
