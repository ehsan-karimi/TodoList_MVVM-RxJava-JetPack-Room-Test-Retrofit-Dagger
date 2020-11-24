package com.example.todolist.Di.Keys;

import androidx.lifecycle.ViewModel;

import java.lang.annotation.Documented;

import javax.inject.Scope;

import dagger.MapKey;

@Documented
@MapKey
public @interface ViewModelKey {
    Class<? extends ViewModel> value();
}
