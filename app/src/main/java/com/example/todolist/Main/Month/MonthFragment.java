package com.example.todolist.Main.Month;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.todolist.Di.ViewModelFactory;
import com.example.todolist.Main.GroupsViewModel;
import com.example.todolist.Main.GroupsViewModelFactory;
import com.example.todolist.Main.Today.TodayGroupsAdapter;
import com.example.todolist.Model.LocalDataSource.RoomConfig.PersonDatabase;
import com.example.todolist.Model.RemoteDataSource.RetrofitConfig.Api_ServiceProvider;
import com.example.todolist.Model.Repositories.GroupsRepository;
import com.example.todolist.R;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.support.AndroidSupportInjection;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MonthFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MonthFragment extends Fragment {

    private View rootView;
    private RecyclerView recyclerView;
    private MonthGroupsAdapter monthGroupsAdapter;
    private LinearLayout emptyStateMonth;
    @Inject
    public GroupsRepository groupsRepository;

    @Inject
    public ViewModelFactory viewModelFactory_new;

    public static MonthFragment newInstance(String text) {
        MonthFragment f = new MonthFragment();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_month, container, false);
        AndroidSupportInjection.inject(this);
        initialize();
        showMonthGroups();
        return rootView;
    }



    private void initialize() {
        emptyStateMonth = rootView.findViewById(R.id.emptyStateMonth);
        recyclerView = rootView.findViewById(R.id.rc_GroupListMonth);


    }


    private void showMonthGroups() {
        GroupsViewModel groupsViewModel = new ViewModelProvider(this, viewModelFactory_new).get(GroupsViewModel.class);
        groupsViewModel.getGroupsMonth().observe(getViewLifecycleOwner(), t -> {

            if (t.size() == 0) {
                emptyStateMonth.setVisibility(View.VISIBLE);
            } else {
                emptyStateMonth.setVisibility(View.GONE);
                RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(rootView.getContext(), 2);
                recyclerView.setLayoutManager(mLayoutManager);
                //    recyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext(), RecyclerView.VERTICAL, false));
                monthGroupsAdapter = new MonthGroupsAdapter(t);
                recyclerView.setAdapter(monthGroupsAdapter);
            }

        });

    }

}