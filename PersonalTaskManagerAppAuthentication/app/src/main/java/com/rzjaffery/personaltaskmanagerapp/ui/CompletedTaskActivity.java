package com.rzjaffery.personaltaskmanagerapp.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.rzjaffery.personaltaskmanagerapp.R;
import com.rzjaffery.personaltaskmanagerapp.viewmodel.TaskViewModel;

import java.util.ArrayList;
import java.util.List;

public class CompletedTaskActivity extends AppCompatActivity {
    private TaskAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed_list);

        DrawerLayout drawerLayout = findViewById(R.id.completed_drawer_layout);
        Toolbar toolbar = findViewById(R.id.completed_toolbar);

// Set up toolbar as the ActionBar
        setSupportActionBar(toolbar);

// Enable hamburger icon
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navView = findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_add_task) {
                startActivity(new Intent(this, AddEditTaskActivity.class));
            } else if (id == R.id.nav_pending) {
                startActivity(new Intent(this, PendingTaskActivity.class));
                Toast.makeText(this, "Showing Pending Tasks", Toast.LENGTH_SHORT).show();
            } else if (id == R.id.nav_completed) {
                startActivity(new Intent(this, CompletedTaskActivity.class));
                Toast.makeText(this, "Showing Completed Tasks", Toast.LENGTH_SHORT).show();
            } else if (id == R.id.nav_exit) {
                finishAffinity(); // Close the app
            }

            drawerLayout.closeDrawers(); // Close drawer after selection
            return true;
        });


        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) RecyclerView recyclerView = findViewById(R.id.completed_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TaskAdapter();
        recyclerView.setAdapter(adapter);

        TaskViewModel taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);

        taskViewModel.getAllTasks().observe(this, allTasks -> {
            List<com.rzjaffery.personaltaskmanagerapp.model.Task> completedTasks = new ArrayList<>();
            for (com.rzjaffery.personaltaskmanagerapp.model.Task task : allTasks) {
                if (task.isCompleted()) {
                    completedTasks.add(task);
                }
            }
            adapter.setTasks(completedTasks);
        });
        taskViewModel.getCompletedTasks().observe(this, tasks -> adapter.setTasks(tasks));

    }
}
