package com.rzjaffery.personaltaskmanagerapp.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.rzjaffery.personaltaskmanagerapp.R;
import com.rzjaffery.personaltaskmanagerapp.viewmodel.TaskViewModel;

public class TaskListActivity extends AppCompatActivity {

    @SuppressLint("SimpleDateFormat")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navView = findViewById(R.id.nav_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

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

        FloatingActionButton buttonAdd = findViewById(R.id.button_add);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        TaskViewModel taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);
        final TaskAdapter adapter = new TaskAdapter(taskViewModel);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(adapter.getSwipeCallback());
        itemTouchHelper.attachToRecyclerView(recyclerView);

        taskViewModel.getAllTasks().observe(this, adapter::setTasks);

        buttonAdd.setOnClickListener(v -> {
            Intent intent = new Intent(TaskListActivity.this, AddEditTaskActivity.class);
            startActivity(intent);
        });
        taskViewModel.getPendingTasks().observe(this, adapter::setTasks);


    }

}
