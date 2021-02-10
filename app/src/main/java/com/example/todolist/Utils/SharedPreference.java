package com.example.todolist.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

public class SharedPreference {

    public Context context;

    @Inject
    public SharedPreference(Context context) {
        this.context = context;
    }

    public Boolean save(String key, String value) {
        try {
            SharedPreferences sharedPref = context.getSharedPreferences("user", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(key, value);
            editor.apply();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String get(String key){
        SharedPreferences sharedPref = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        return sharedPref.getString(key, null);
    }

}
