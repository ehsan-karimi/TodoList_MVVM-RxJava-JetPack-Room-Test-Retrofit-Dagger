package com.example.todolist.AddGroups;

import android.annotation.SuppressLint;
import android.graphics.Color;
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

    //declare interface
    private OnItemClicked onClick;

    //make interface like this
    public interface OnItemClicked {
        void onItemClick(int id);
    }

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

    int row;

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
        holder.bind(employeeModel.get(position), position);


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
            cardView = itemView.findViewById(R.id.card);
        }

        @SuppressLint("SetTextI18n")
        public void bind(Icons icons, int position) {
            icon.setImageResource(icons.getId());

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    row = position;
                    notifyDataSetChanged();
                }
            });
            if (row == position) {
                cardView.setBackgroundColor(itemView.getResources().getColor(R.color.colorCountTaskEvents));
            } else {
                cardView.setBackgroundColor(Color.WHITE);
            }
        }
    }

    public void setOnClick(OnItemClicked onClick) {
        this.onClick = onClick;
    }
}


