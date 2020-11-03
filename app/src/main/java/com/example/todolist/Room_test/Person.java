package com.example.todolist.Room_test;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "person")
public class Person {
    @PrimaryKey(autoGenerate = true)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "city")
    private String city;

    public Person(int id, String name, String city){
        this.id = id;
        this.name = name;
        this.city = city;
    }

    @Ignore
    public Person(String name, String city){
        this.name = name;
        this.city = city;
    }
}
