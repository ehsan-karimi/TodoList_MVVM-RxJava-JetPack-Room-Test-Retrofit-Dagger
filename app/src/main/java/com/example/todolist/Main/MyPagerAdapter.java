package com.example.todolist.Main;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.todolist.Main.Month.MonthFragment;
import com.example.todolist.Main.Today.TodayFragment;
import com.example.todolist.Main.Week.WeekFragment;

public class MyPagerAdapter extends FragmentStateAdapter {

    private static final int NUM_PAGES = 3;
    //The pager widget, which handles animation and allows swiping horizontally to access previous and next wizard steps.

    public MyPagerAdapter(FragmentActivity fa) {
        super(fa);
    }


    @Override
    public Fragment createFragment(int pos) {
        switch (pos) {
            case 0: {
                return TodayFragment.newInstance("fragment 1");
            }
            case 1: {

                return WeekFragment.newInstance("fragment 2");
            }
            case 2: {
                return MonthFragment.newInstance("fragment 3");
            }
            default:
                return TodayFragment.newInstance("fragment 1, Default");
        }
    }

    @Override
    public int getItemCount() {
        return NUM_PAGES;
    }
}
