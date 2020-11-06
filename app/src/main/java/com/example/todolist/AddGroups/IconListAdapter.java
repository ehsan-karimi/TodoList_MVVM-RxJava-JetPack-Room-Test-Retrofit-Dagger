package com.example.todolist.AddGroups;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.Model.Entities.Icons;
import com.example.todolist.R;
import com.google.android.material.card.MaterialCardView;

import java.util.List;


public class IconListAdapter extends RecyclerView.Adapter<IconListAdapter.EmployeeViewHolder> {
    private List<Icons> employeeModel;

    public IconListAdapter(List<Icons> employeeModel) {
        this.employeeModel = employeeModel;
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EmployeeViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_icon_list, parent, false));
    }

    public void addEmployee(Icons employeeModel) {
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
        public void bind(Icons employeeModel) {
//            fullNameTv.setText(employeeModel.getFirstname() + " " + employeeModel.getLastname());
//            courseTitleTv.setText(employeeModel.getLastname());
//            scoreTv.setText(String.valueOf(employeeModel.getPhonenumber()));
//            firstCharacterTv.setText(employeeModel.getId());
            icon.setImageResource(employeeModel.getId());
        }
    }
}


