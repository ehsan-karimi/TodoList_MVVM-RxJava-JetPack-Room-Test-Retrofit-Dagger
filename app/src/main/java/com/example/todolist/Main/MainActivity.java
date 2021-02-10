package com.example.todolist.Main;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.todolist.AddGroups.AddGroupActivity;
import com.example.todolist.Di.ViewModelFactory;
import com.example.todolist.R;
import com.example.todolist.entry.EntryActivity;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasFragmentInjector;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements HasFragmentInjector {

    public static ViewPager2 viewPager;
    // The pager adapter, which provides the pages to the view pager widget.
    private FragmentStateAdapter pagerAdapter;
    // Arrey of strings FOR TABS TITLES
    private String[] titles = new String[]{"TODAY", "WEEK", "MONTH"};
    // tab titles
    private ExtendedFloatingActionButton floatingActionButton;

    private GroupsViewModel groupsViewModel;
    @Inject
    public ViewModelFactory viewModelFactory_new;

    private Disposable disposable;

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AndroidInjection.inject(this);
        initialize();

    }

    private void initialize() {
        //recyclerView = findViewById(R.id.taskListRv);
        viewPager = findViewById(R.id.view_pager);
        pagerAdapter = new MyPagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);
        floatingActionButton = findViewById(R.id.extended_fab);
        floatingActionButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddGroupActivity.class);
            startActivity(intent);
        });
        //inflating tab layout
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        //displaying tabs
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> tab.setText(titles[position])).attach();
        groupsViewModel = new ViewModelProvider(this, viewModelFactory_new).get(GroupsViewModel.class);
        if (checkFirstTime()){
            Intent intent = new Intent(getApplicationContext(), EntryActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private Boolean checkFirstTime() {
        String result = groupsViewModel.getDataSharedPreferences("first_time");
        return result == null;
    }

    private void checkInternet() {
        groupsViewModel.isInternetWorking().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<Boolean>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                disposable = d;
            }

            @Override
            public void onSuccess(@NonNull Boolean aBoolean) {
                if (checkFirstTime()){
                    Intent intent = new Intent(getApplicationContext(), EntryActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                if (checkFirstTime())
                dialog();
            }
        });
    }

    private void dialog(){
        new AlertDialog.Builder(this)
                .setTitle("Delete entry")
                .setMessage("Are you sure you want to delete this entry?")

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Continue with delete operation
                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.d
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }

    @Override
    public AndroidInjector<Fragment> fragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }

}