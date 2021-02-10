package com.example.todolist.Model.Entities;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "tbl_groups")
public class Groups {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int icon;
    private String label;
    private String category;
    private boolean synced;
    private boolean deleted;
    private boolean updated;
    private int donePercent;
    private int tasksCount;

    public Groups(int id, int icon, String label, String category, boolean synced, boolean deleted, boolean updated, int donePercent, int tasksCount) {
        this.id = id;
        this.icon = icon;
        this.label = label;
        this.category = category;
        this.synced = synced;
        this.deleted = deleted;
        this.updated = updated;
        this.donePercent = donePercent;
        this.tasksCount = tasksCount;
    }

    @Ignore
    public Groups(int icon, String label, String category, boolean synced, boolean deleted, boolean updated, int donePercent, int tasksCount) {
        this.icon = icon;
        this.label = label;
        this.category = category;
        this.synced = synced;
        this.deleted = deleted;
        this.updated = updated;
        this.donePercent = donePercent;
        this.tasksCount = tasksCount;
    }

    public int getTasksCount() {
        return tasksCount;
    }

    public void setTasksCount(int tasksCount) {
        this.tasksCount = tasksCount;
    }

    public int getDonePercent() {
        return donePercent;
    }

    public void setDonePercent(int donePercent) {
        this.donePercent = donePercent;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isUpdated() {
        return updated;
    }

    public void setUpdated(boolean updated) {
        this.updated = updated;
    }

    public boolean isSynced() {
        return synced;
    }

    public void setSynced(boolean synced) {
        this.synced = synced;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
