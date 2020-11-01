package com.example.todolist;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    private TaskAdapter taskAdapter;

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
    }

    private void showList(String result) {
        java.util.List<TaskEntity> taskEntityList = new Gson().fromJson(result, new TypeToken<List<TaskEntity>>() {
        }.getType());

        //  recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false));
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(rootView.getContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        taskAdapter = new TaskAdapter(taskEntityList);
        recyclerView.setAdapter(taskAdapter);
    }

}