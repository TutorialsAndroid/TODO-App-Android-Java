package com.app.todo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TaskAdapter taskAdapter;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        databaseHelper = new DatabaseHelper(this);
        loadTasks();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> new AddTaskDialog(this, databaseHelper, taskAdapter).show());
    }

    private void loadTasks() {
        List<Task> taskList = databaseHelper.getAllTasks();
        taskAdapter = new TaskAdapter(this, taskList, databaseHelper);
        recyclerView.setAdapter(taskAdapter);
    }
}
