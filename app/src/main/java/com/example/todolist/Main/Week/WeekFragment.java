package com.example.todolist.Main.Week;

import android.os.Bundle;

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

import com.example.todolist.AddGroups.AddTaskGroupViewModel;
import com.example.todolist.AddGroups.AddTaskGroupViewModelFactory;
import com.example.todolist.Main.GroupsViewModel;
import com.example.todolist.Main.GroupsViewModelFactory;
import com.example.todolist.Main.TaskGroupAdapter;
import com.example.todolist.Model.Repositories.GroupsRepository;
import com.example.todolist.Model.LocalDataSource.RoomConfig.PersonDatabase;
import com.example.todolist.Model.RemoteDataSource.RetrofitConfig.Api_ServiceProvider;
import com.example.todolist.R;
import com.example.todolist.Model.Entities.Tasks;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WeekFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WeekFragment extends Fragment {


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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_week, container, false);
        initialize();
        showWeekGroups();
        return rootView;
    }

    private void initialize() {
        recyclerView = rootView.findViewById(R.id.taskListRv);
        emptyStateWeek = rootView.findViewById(R.id.emptyStateWeek);
    }

    private void showWeekGroups(){
        GroupsViewModel groupsViewModel = new ViewModelProvider(this, new GroupsViewModelFactory(new GroupsRepository(PersonDatabase.getInstance(rootView.getContext().getApplicationContext()).groupsDao(), Api_ServiceProvider.getApi_interface()), 0)).get(GroupsViewModel.class);
        groupsViewModel.getGroupsWeek().observe(getViewLifecycleOwner(), t -> {
            if (t.size() < 1) {
                emptyStateWeek.setVisibility(View.VISIBLE);
            } else {
                Log.e("Week", "initialize: ");
                RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(rootView.getContext(), 2);
                recyclerView.setLayoutManager(mLayoutManager);
                weekGroupsAdapter = new WeekGroupsAdapter(t);
                recyclerView.setAdapter(weekGroupsAdapter);
            }
        });

        groupsViewModel.getError().observe(getViewLifecycleOwner(), e ->{
            Toast.makeText(rootView.getContext(),"Failed Sync Your Groups!!!",Toast.LENGTH_LONG).show();
        });
    }

}