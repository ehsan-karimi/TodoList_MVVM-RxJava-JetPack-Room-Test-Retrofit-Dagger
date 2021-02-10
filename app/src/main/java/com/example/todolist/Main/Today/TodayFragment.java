package com.example.todolist.Main.Today;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

import com.example.todolist.Di.ViewModelFactory;
import com.example.todolist.Main.GroupsViewModel;
import com.example.todolist.Model.Repositories.GroupsRepository;
import com.example.todolist.R;
import com.example.todolist.entry.EntryActivity;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class TodayFragment extends Fragment {

    private View rootView;
    private RecyclerView recyclerView;
    private TodayGroupsAdapter todayGroupsAdapter;
    private LinearLayout emptyStateToday;
    @Inject
    public GroupsRepository groupsRepository;
    private GroupsViewModel groupsViewModel;
    private Disposable disposable;

    @Inject
    public ViewModelFactory viewModelFactory_new;

//    @Inject
//    public ViewModelFactory viewModelFactory;

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
        AndroidSupportInjection.inject(this);
        initialize();
      //  checkFirstTime();
        showTodayGroups();
        return rootView;
    }

    private void initialize() {
        recyclerView = rootView.findViewById(R.id.rc_GroupListToday);
        emptyStateToday = rootView.findViewById(R.id.emptyStateToday);
       // groupsViewModel = new ViewModelProvider(this, new GroupsViewModelFactory(groupsRepository, 1)).get(GroupsViewModel.class);
        groupsViewModel = new ViewModelProvider(this, viewModelFactory_new).get(GroupsViewModel.class);
    }


    private void showTodayGroups() {


//        GroupsViewModel groupsViewModel = new ViewModelProvider(this, new GroupsViewModelFactory(groupsRepository, 1)).get(GroupsViewModel.class);

        groupsViewModel.getGroupsToday().observe(getViewLifecycleOwner(), t -> {

            if (t.size() < 1) {
                emptyStateToday.setVisibility(View.VISIBLE);
            } else {
                emptyStateToday.setVisibility(View.GONE);
                RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(rootView.getContext(), 2);
                recyclerView.setLayoutManager(mLayoutManager);
                //    recyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext(), RecyclerView.VERTICAL, false));
                todayGroupsAdapter = new TodayGroupsAdapter(t);
                recyclerView.setAdapter(todayGroupsAdapter);
            }

        });

//        groupsViewModel.getError().observe(getViewLifecycleOwner(), e -> {
//            Toast.makeText(rootView.getContext(), "Failed Sync Your Groups!!!", Toast.LENGTH_LONG).show();
//        });
    }

    private void checkInternet() {
        groupsViewModel.isInternetWorking().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<Boolean>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                disposable = d;
            }

            @Override
            public void onSuccess(@NonNull Boolean aBoolean) {
                Intent intent = new Intent(rootView.getContext(), EntryActivity.class);
                startActivity(intent);
                getActivity().finish();
            }

            @Override
            public void onError(@NonNull Throwable e) {
               dialog();
            }
        });
    }

    private void dialog(){
        new AlertDialog.Builder(rootView.getContext())
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

    private void checkFirstTime(){
//        boolean s = groupsViewModel.saveDataSharedPreferences("first_time", "test");
//        Log.e("TAG", "checkFirstTime: " + s );
        String result = groupsViewModel.getDataSharedPreferences("first_time");
        if (result != null)
            checkInternet();
//        SharedPreferences sharedPref = rootView.getContext().getSharedPreferences("user", Context.MODE_PRIVATE);
//        sharedPref.getString("key", null);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        disposable.dispose();
    }
}