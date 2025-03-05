package com.app.todo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    private Context context;
    private List<Task> taskList;
    private DatabaseHelper databaseHelper;

    public TaskAdapter(Context context, List<Task> taskList, DatabaseHelper databaseHelper) {
        this.context = context;
        this.taskList = taskList;
        this.databaseHelper = databaseHelper;
    }

    public void updateTaskList(List<Task> newTaskList) {
        this.taskList.clear();
        this.taskList.addAll(newTaskList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_task, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = taskList.get(position);
        holder.textViewTitle.setText(task.getTitle());

        holder.buttonDelete.setOnClickListener(v -> {
            databaseHelper.deleteTask(task.getId());
            taskList.remove(position);
            notifyItemRemoved(position);
        });

        holder.buttonEdit.setOnClickListener(v -> new EditTaskDialog(context, databaseHelper, this, task).show());
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle;
        ImageButton buttonDelete, buttonEdit;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            buttonDelete = itemView.findViewById(R.id.buttonDelete);
            buttonEdit = itemView.findViewById(R.id.buttonEdit);
        }
    }
}
