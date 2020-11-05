package com.example.todolist.Main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.todolist.AddGroups.AddTaskGroupViewModel;
import com.example.todolist.AddGroups.AddTaskGroupViewModelFactory;
import com.example.todolist.Model.GroupsRepository;
import com.example.todolist.Model.Room.GroupsDao;
import com.example.todolist.Model.Room.PersonDatabase;
import com.example.todolist.Network.Api_ServiceProvider;
import com.example.todolist.R;
import com.example.todolist.Model.TaskEntity;
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
    private TaskGroupAdapter taskGroupAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
     //   return inflater.inflate(R.layout.fragment_week, container, false);
        rootView = inflater.inflate(R.layout.fragment_week, container, false);
        initialize();
        showList(getResources().getString(R.string.json));
      //  linearLayout = (LinearLayout) rootView.findViewById(R.id.linearlayout);
        return rootView;
    }

    private void initialize(){
        recyclerView = rootView.findViewById(R.id.taskListRv);
        AddTaskGroupViewModel taskGroupViewModel = new ViewModelProvider(this, new AddTaskGroupViewModelFactory()).get(AddTaskGroupViewModel.class);
        taskGroupViewModel.getGroups().observe(getViewLifecycleOwner(), t->{
            Log.e("Fragment:", "initialize: " + t.get(0).getLabel() );
        });
//        Log.e("Fragment:", "initialize: " + taskGroupViewModel.getGroups().getValue().get(0).getLabel() );
    }

    private void showList(String result) {
        java.util.List<TaskEntity> taskEntityList = new Gson().fromJson(result, new TypeToken<List<TaskEntity>>() {
        }.getType());

        //  recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false));
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(rootView.getContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        taskGroupAdapter = new TaskGroupAdapter(taskEntityList);
        recyclerView.setAdapter(taskGroupAdapter);
    }

}