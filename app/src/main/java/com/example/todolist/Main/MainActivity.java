package com.example.todolist.Main;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.todolist.AddGroups.AddTaskGroupActivity;
import com.example.todolist.R;
import com.example.todolist.Utils.AppExecutors;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasFragmentInjector;

public class MainActivity extends AppCompatActivity implements HasFragmentInjector {

    public static ViewPager2 viewPager;
    // The pager adapter, which provides the pages to the view pager widget.
    private FragmentStateAdapter pagerAdapter;
    // Arrey of strings FOR TABS TITLES
    private String[] titles = new String[]{"TODAY", "WEEK", "MONTH"};
    // tab titles
    private ExtendedFloatingActionButton floatingActionButton;

    private int startNumber, endNumber;

    private static final Integer ADD_GROUP_REQUEST_ID = 1001;

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AndroidInjection.inject(this);
        initialize();

//        int j = R.drawable.ic_home;
//        Log.e("For Me:", "onCreate: " + String.valueOf(j));
//
//        int j2 = R.drawable.ic_mortarboard;
//        Log.e("For Me:", "onCreate: " + String.valueOf(j2));
//
//        int j3 = R.drawable.ic_note;
//        Log.e("For Me:", "onCreate: " + String.valueOf(j3));
//
//        int j4 = R.drawable.ic_suitcase;
//        Log.e("For Me:", "onCreate: " + String.valueOf(j4));
//
//
//        int j5 = R.drawable.ic_trophy;
//        Log.e("For Me:", "onCreate: " + String.valueOf(j5));

//        evenNumbers(2, 9);
//        Log.e("Multiplication: ", String.valueOf(multiplication()[4][4]));
//        fibonacci(10);
    }

//    private void fibonacci(int totalSequence) {
//        int counter = 0;
//        int[] result = new int[totalSequence];
//        while (counter < totalSequence) {
//            if (counter == 0) {
//                Log.i("Fibonacci: ", "0");
//                result[counter] = counter;
//            } else if (counter == 1) {
//                Log.i("Fibonacci: ", "1");
//                result[counter] = counter;
//            } else {
//                result[counter] = result[counter - 1] + result[counter - 2];
//                Log.i("Fibonacci: ", String.valueOf(result[counter]));
//            }
//            counter++;
//        }
//    }
//
//    private int[][] multiplication() {
//        int[][] multiplication = new int[5][5];
//        int row = 1, column = 1;
//        while (row <= 5) {
//            while (column <= 5) {
//                multiplication[row - 1][column - 1] = row * column;
//                column++;
//            }
//            column = 1;
//            row++;
//        }
//        return multiplication;
//    }
//
//    private void evenNumbers(int firstNumber, int secondNumber) {
//        if (firstNumber < secondNumber) {
//            this.startNumber = firstNumber;
//            this.endNumber = secondNumber;
//        } else {
//            this.startNumber = secondNumber;
//            this.endNumber = firstNumber;
//        }
//        while (startNumber <= endNumber) {
//            if (startNumber % 2 == 0) {
//                Log.i("Even Numbers: ", String.valueOf(startNumber));
//            }
//            startNumber++;
//        }
//    }


    private void initialize() {
        //recyclerView = findViewById(R.id.taskListRv);

        viewPager = findViewById(R.id.view_pager);
        pagerAdapter = new MyPagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);
        floatingActionButton = findViewById(R.id.extended_fab);
        floatingActionButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddTaskGroupActivity.class);
            startActivity(intent);
        });
//inflating tab layout
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
//displaying tabs
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> tab.setText(titles[position])).attach();
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