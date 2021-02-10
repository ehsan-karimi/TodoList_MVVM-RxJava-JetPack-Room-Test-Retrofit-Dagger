package com.example.todolist.Main.Week;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.Di.ViewModelFactory;
import com.example.todolist.Main.GroupsViewModel;
import com.example.todolist.Main.GroupsViewModelFactory;
import com.example.todolist.Model.Repositories.GroupsRepository;
import com.example.todolist.R;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WeekFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WeekFragment extends Fragment {

    @Inject
    public ViewModelFactory viewModelFactory_new;

    public static WeekFragment newInstance(String text) {
        WeekFragment f = new WeekFragment();
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

    private RecyclerView recyclerView;
    private View rootView;
    private WeekGroupsAdapter weekGroupsAdapter;
    private LinearLayout emptyStateWeek;
    @Inject
    public GroupsRepository groupsRepository;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_week, container, false);
        AndroidSupportInjection.inject(this);
        initialize();
        showWeekGroups();
        return rootView;
    }

    private void initialize() {
        recyclerView = rootView.findViewById(R.id.taskListRv);
        emptyStateWeek = rootView.findViewById(R.id.emptyStateWeek);
    }


    private void showWeekGroups() {
        GroupsViewModel groupsViewModel = new ViewModelProvider(this, viewModelFactory_new).get(GroupsViewModel.class);
        groupsViewModel.getGroupsWeek().observe(getViewLifecycleOwner(), t -> {
            if (t.size() < 1) {
                emptyStateWeek.setVisibility(View.VISIBLE);
            } else {
                emptyStateWeek.setVisibility(View.GONE);
                RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(rootView.getContext(), 2);
                recyclerView.setLayoutManager(mLayoutManager);
                weekGroupsAdapter = new WeekGroupsAdapter(t);
                recyclerView.setAdapter(weekGroupsAdapter);
            }
        });


    }

}