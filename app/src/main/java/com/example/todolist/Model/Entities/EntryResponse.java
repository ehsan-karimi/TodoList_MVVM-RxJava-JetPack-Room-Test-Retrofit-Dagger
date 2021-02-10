package com.example.todolist.Model.Entities;

public class EntryResponse {
    private String message;
    private String jwt;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public EntryResponse(String message, String jwt) {
        this.message = message;
        this.jwt = jwt;
    }
}
