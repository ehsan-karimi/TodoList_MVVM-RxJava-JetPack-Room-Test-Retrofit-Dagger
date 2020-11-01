package com.example.todolist;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.List;


public class IconListAdapter extends RecyclerView.Adapter<IconListAdapter.EmployeeViewHolder> {
    private List<IconListModel> employeeModel;

    public IconListAdapter(List<IconListModel> employeeModel) {
        this.employeeModel = employeeModel;
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EmployeeViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_icon_list, parent, false));
    }

    public void addEmployee(IconListModel employeeModel) {
        this.employeeModel.add(0, employeeModel);
        notifyItemInserted(0);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
        holder.bind(employeeModel.get(position));
    }

    @Override
    public int getItemCount() {
        return employeeModel.size();
    }

    public class EmployeeViewHolder extends RecyclerView.ViewHolder {

        private ImageView icon;
        private MaterialCardView cardView;

        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);

           icon = itemView.findViewById(R.id.icon);



//            cardView = itemView.findViewById(R.id.card);
//
//            cardView.setOnClickListener(v->{
//                Intent intent = new Intent(itemView.getContext(),InsideTodayActivity.class);
//                itemView.getContext().startActivity(intent);
//            });

        }

        @SuppressLint("SetTextI18n")
        public void bind(IconListModel employeeModel) {
//            fullNameTv.setText(employeeModel.getFirstname() + " " + employeeModel.getLastname());
//            courseTitleTv.setText(employeeModel.getLastname());
//            scoreTv.setText(String.valueOf(employeeModel.getPhonenumber()));
//            firstCharacterTv.setText(employeeModel.getId());
            icon.setImageResource(employeeModel.getId());
        }
    }
}


