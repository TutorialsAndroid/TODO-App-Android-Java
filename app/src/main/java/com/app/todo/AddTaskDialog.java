package com.app.todo;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class AddTaskDialog extends Dialog {
    private DatabaseHelper databaseHelper;
    private TaskAdapter taskAdapter;
    private Context context;

    public AddTaskDialog(Context context, DatabaseHelper databaseHelper, TaskAdapter taskAdapter) {
        super(context);
        this.context = context;
        this.databaseHelper = databaseHelper;
        this.taskAdapter = taskAdapter;

        requestWindowFeature(Window.FEATURE_NO_TITLE); // Remove default title
        setContentView(R.layout.dialog_add_task);

        // Set the dialog width to match the screen width
        if (getWindow() != null) {
            getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        }

        EditText editTextTask = findViewById(R.id.editTextTask);
        Button buttonAdd = findViewById(R.id.buttonAdd);

        buttonAdd.setOnClickListener(v -> {
            String taskTitle = editTextTask.getText().toString().trim();
            if (!taskTitle.isEmpty()) {
                databaseHelper.addTask(taskTitle);

                // Fetch updated tasks and refresh adapter
                List<Task> updatedTasks = databaseHelper.getAllTasks();
                taskAdapter.updateTaskList(updatedTasks);

                dismiss();
            }
        });
    }
}