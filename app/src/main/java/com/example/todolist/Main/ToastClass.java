package com.example.todolist.Main;

import android.content.Context;
import android.widget.Toast;

import javax.inject.Inject;

public class ToastClass {

    @Inject
    public ToastClass(Context context) {
        Toast.makeText(context.getApplicationContext(),"hello",Toast.LENGTH_LONG).show();
    }
}
