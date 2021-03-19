package com.example.todolist.entry;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.todolist.Di.ViewModelFactory;
import com.example.todolist.Main.MainActivity;
import com.example.todolist.Model.Entities.EntryResponse;
import com.example.todolist.R;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.gson.JsonObject;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class EntryActivity extends AppCompatActivity {

    private EditText ed_email;
    private EditText ed_password;
    private ExtendedFloatingActionButton save_extended_fab;
    private Disposable disposable;

    @Inject
    public ViewModelFactory viewModelFactory_new;

    private EntryViewModel entryViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_login);
        AndroidInjection.inject(this);
        initialize();
        setOnClick();
    }

    private void initialize() {
        ed_email = findViewById(R.id.ed_email);
        ed_password = findViewById(R.id.ed_password);
        save_extended_fab = findViewById(R.id.extended_fab);
        entryViewModel = new ViewModelProvider(this, viewModelFactory_new).get(EntryViewModel.class);
    }

    private void setOnClick() {
        save_extended_fab.setOnClickListener(v -> {
            if (ed_email.getText().toString().trim().length() > 10 && ed_password.getText().toString().length() > 4)
                saveUser();
        });
    }

    private void saveUser() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("email", ed_email.getText().toString().trim());
        jsonObject.addProperty("password", ed_password.getText().toString());

        entryViewModel.entry(jsonObject).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<EntryResponse>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                disposable = d;
            }

            @Override
            public void onSuccess(@NonNull EntryResponse entryResponse) {
                    entryViewModel.saveDataSharedPreferences("first_time", "no");
                    entryViewModel.saveDataSharedPreferences("token", entryResponse.getJwt());
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
            }

            @Override
            public void onError(@NonNull Throwable e) {
                if (e.getMessage().equals("HTTP 401 Unauthorized")) {
                    Toast.makeText(getApplicationContext(), "Password is wrong!!!", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getApplicationContext(), "Please try again!!!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        disposable.dispose();
    }
}
