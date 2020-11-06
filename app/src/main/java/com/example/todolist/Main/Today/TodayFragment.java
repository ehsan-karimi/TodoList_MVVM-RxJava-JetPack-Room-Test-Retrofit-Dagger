package com.example.todolist.Main.Today;

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

import com.example.todolist.Main.GroupsViewModel;
import com.example.todolist.Main.GroupsViewModelFactory;
import com.example.todolist.Model.LocalDataSource.RoomConfig.PersonDatabase;
import com.example.todolist.Model.RemoteDataSource.RetrofitConfig.Api_ServiceProvider;
import com.example.todolist.Model.Repositories.GroupsRepository;
import com.example.todolist.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TodayFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TodayFragment extends Fragment {

    private View rootView;
    private RecyclerView recyclerView;
    private TodayGroupsAdapter todayGroupsAdapter;
    private LinearLayout emptyStateToday;

    public static TodayFragment newInstance(String text) {
        TodayFragment f = new TodayFragment();
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
        rootView = inflater.inflate(R.layout.fragment_today, container, false);
        initialize();
        return rootView;
    }

    private void initialize(){
        recyclerView = rootView.findViewById(R.id.rc_GroupListToday);
        emptyStateToday = rootView.findViewById(R.id.emptyStateToday);

                GroupsViewModel groupsViewModel = new ViewModelProvider(this, new GroupsViewModelFactory(new GroupsRepository(PersonDatabase.getInstance(rootView.getContext().getApplicationContext()).groupsDao(), Api_ServiceProvider.getApi_interface()))).get(GroupsViewModel.class);
        groupsViewModel.getGroupsToday().observe(getViewLifecycleOwner(), t->{

            if (t.size() < 1){
                emptyStateToday.setVisibility(View.VISIBLE);
            }else {
                Log.e("Day", "initialize: " );
                RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(rootView.getContext(), 2);
                recyclerView.setLayoutManager(mLayoutManager);
            //    recyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext(), RecyclerView.VERTICAL, false));
                todayGroupsAdapter = new TodayGroupsAdapter(t);
                recyclerView.setAdapter(todayGroupsAdapter);
            }

        });
    }

}