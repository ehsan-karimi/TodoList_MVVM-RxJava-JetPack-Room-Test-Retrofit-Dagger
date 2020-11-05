package com.example.todolist.Model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "tbl_groups")
public class Groups {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private int icon;
    private String label;
    private String category;

    public Groups(long id, int icon, String label, String category){
        this.id = id;
        this.icon = icon;
        this.label = label;
        this.category = category;
    }

    @Ignore
    public Groups(int icon, String label, String category){
        this.icon = icon;
        this.label = label;
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
