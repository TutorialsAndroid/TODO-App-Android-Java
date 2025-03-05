package com.app.todo;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class EditTaskDialog extends Dialog {
    public EditTaskDialog(Context context, DatabaseHelper databaseHelper, TaskAdapter taskAdapter, Task task) {
        super(context);

        requestWindowFeature(Window.FEATURE_NO_TITLE); // Remove default title
        setContentView(R.layout.dialog_edit_task);

        // Set the dialog width to match the screen width
        if (getWindow() != null) {
            getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        }


        EditText editTextTask = findViewById(R.id.editTextTask);
        Button buttonUpdate = findViewById(R.id.buttonUpdate);

        editTextTask.setText(task.getTitle());

        buttonUpdate.setOnClickListener(v -> {
            String newTitle = editTextTask.getText().toString().trim();
            if (!newTitle.isEmpty()) {
                databaseHelper.updateTask(task.getId(), newTitle);

                // Fetch updated tasks and refresh adapter
                List<Task> updatedTasks = databaseHelper.getAllTasks();
                taskAdapter.updateTaskList(updatedTasks);

                dismiss();
            }
        });
    }
}
