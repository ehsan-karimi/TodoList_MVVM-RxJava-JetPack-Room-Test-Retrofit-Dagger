package com.example.todolist.Model.Entities;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "tbl_tasks")
public class Tasks {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int groupId;
    private String content;
    private long date;
    private boolean repeatTask;
    private boolean done;
    private boolean synced;
    private boolean deleted;
    private boolean updated;

    public Tasks(int id, int groupId, String content, long date, boolean repeatTask, boolean done, boolean synced, boolean deleted, boolean updated) {
        this.id = id;
        this.groupId = groupId;
        this.content = content;
        this.date = date;
        this.repeatTask = repeatTask;
        this.done = done;
        this.synced = synced;
        this.deleted = deleted;
        this.updated = updated;
    }

    @Ignore
    public Tasks(int groupId, String content, long date, boolean repeatTask, boolean done, boolean synced, boolean deleted, boolean updated) {
        this.groupId = groupId;
        this.content = content;
        this.date = date;
        this.repeatTask = repeatTask;
        this.done = done;
        this.synced = synced;
        this.deleted = deleted;
        this.updated = updated;
    }

    public boolean isRepeatTask() {
        return repeatTask;
    }

    public void setRepeatTask(boolean repeatTask) {
        this.repeatTask = repeatTask;
    }

    public boolean isSynced() {
        return synced;
    }

    public void setSynced(boolean synced) {
        this.synced = synced;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
